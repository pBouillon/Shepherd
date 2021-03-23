import { config } from './config.js';

/**
 * @const {number} MEDIA_NAME_MAX_LENGTH - Maximum length for media name
 */
const MEDIA_NAME_MAX_LENGTH = 15;

/**
 * @const {Object} api - Axios instance of the API
 */
const api = axios.create({
  baseUrl: config.apiUrl,
  headers: { 'Content-Type': 'application/json' }
});

/**
 * @const {string} bodyForUnknownMedia - HTML string for the body content of unknown media
 */
const bodyForUnknownMedia = `
  <div class="container">
    <div class="row text-center h-50 d-flex align-content-center">
      <p class="pt-4">This website is not yet rated by Shepherd.</p>
    </div>

    <div class="row text-center h-50 d-flex align-content-center">
      <p class="pt-2">Help the community by <a class="shepherd-link" href="${config.shepherdUri}" target="_blank">suggesting this media</a> on Shepherd.</p>
    </div>
  </div>
`;

/**
 * @const {Object} buttons - Buttons 
 */
const buttons = {
  suspicious: document.getElementById('buttonVoteSuspicious'),
  trustworthy: document.getElementById('buttonVoteTrustworthy')
};

/**
 * Hold the current media's information
 */
let currentMedia = { };

/**
 * Initialize listener events for the buttons
 */
function attachListenersToVoteButtons() {
  buttons.suspicious.addEventListener('click', sendNegativeVote);
  buttons.trustworthy.addEventListener('click', sendPositiveVote);
};

/**
 * Create a new stylized tag from a label
 * @param {string} label - Label of the tag to be created
 * @returns {HTMLElement} - Generated tag HTML element
 */
function createTag(label) {
  let tag = document.createElement('span');

  tag = styleElementAsTag(tag);
  tag.innerHTML = label;

  return tag;
};

/**
 * Retrieve the information related to the current media from the API
 * @param {string} website - The URL of the website currently displayed
 * @returns {Object} - The media details
 */
async function fetchMediaByWebsite(website) {
  const response = await api.get(
    getUrlForMediaSearchByWebsite(website)
  );

  let media = response.data.content[0];
  return media;
};

/**
 * @returns {string} - URI of the current page
 */
async function getCurrentPageUri() {
  // Create a promise for getting the tab URL
  const promise = new Promise(resolve => {
    chrome.tabs.query({ active: true, lastFocusedWindow: true }, tabs => {
      resolve(tabs[0].url);
    });
  });

  // Extract website URI from the tab's full URL
  let pageFullUrl = await promise;
  let pageUri = pageFullUrl.match(/^https:\/\/[^\/]{1,50}/g)[0];
  
  return pageUri;
};

/**
 * Generate configuration dictionary for the gauge instanciation
 * @param {Object} media - The media for which the gauge will be set up
 * @returns {Object} - The preconfigured configuration object
 */
function getGaugeConfigurationFor(media) {
  return {
    min: config.mediaRateMin,
    max: config.mediaRateMax,
    value: Math.round(media.trustReport.rate),
    label: (val) => val + '%'
  }
};

/**
 * Get API URL for the media resource
 * @param {Object} media - Media object
 * @returns {URL} - API resource URL for the given media
 */
function getUrlForMedia(media) {
  return config.apiUri + "medias/" + media.id;
};

/**
 * Get API URL for searching medias by website
 * @param {string} website - Website URI to search
 * @returns {URL} - API URL for the meadia search
 */
function getUrlForMediaSearchByWebsite(website) {
  return config.apiUri + "medias?website=" + website;
};

/**
 * Get API URL for the media resource
 * @param {Object} media - Media object
 * @returns {URL} - API resource URL for the given media
 */
function getUrlForMedias(media) {
  return config.apiUri + "medias";
};

/**
 * Get API URL for the media's votes resource
 * @param {Object} media - Media object
 */
function getUrlForMediaVote(media) {
  return config.apiUri + "medias/" + media.id + "/votes";
};

/**
 * Get the trimmed version of a long string if needed
 * The string will remain untouched if there is no need to trim it
 * @param {string} value - String to be trimmed
 * @returns {string} - Trimmed string
 */
function getTrimmed(value) {
  return value.length <= MEDIA_NAME_MAX_LENGTH
    ? value
    : value.substring(0, MEDIA_NAME_MAX_LENGTH) + '...';
};

/**
 * @param {string} uri - Website URI of the media
 * @return {boolean} - Boolean of whether the media exists in the database
 */
async function isKnownMedia(uri) {
  const response = await api.get(
    getUrlForMediaSearchByWebsite(uri)
  );

  let atLeastOneResult = response.data.totalElements > 0;
  return atLeastOneResult;
}

/**
 * Load view for a media that is in the database
 */
function loadViewForKnownMedia() {
  // Load title
  document.getElementById('mediaName').innerHTML = currentMedia.name;
  
  // Populate tags
  // populateTagsFrom(currentMedia);

  // Initialize gauge
  Gauge(
    document.getElementById('rateGauge'),
    getGaugeConfigurationFor(currentMedia)
  );
};

/**
 * Load view for a media that is not in the database
 */
function loadViewForUnknownMedia() {
  let body = document.querySelector('body');
  let html = document.querySelector('html');
  
  body.innerHTML = bodyForUnknownMedia;
  html.className = "unknown";
};

/**
 * Populate the extension's main window
 */
async function populateContent() {
  // Fetch media from API based on current page's domain
  let uri = await getCurrentPageUri();
  let isKnown = await isKnownMedia(uri);

  if (!isKnown) {
    loadViewForUnknownMedia();
    return;
  }

  currentMedia = await fetchMediaByWebsite(uri);
  loadViewForKnownMedia();
};

/**
 * Create and add the media tags
 * @param {Object} media - Media object
 */
function populateTagsFrom(media) {
  let tags = document.getElementById('tagList');

  media.tags.forEach(function(label) {
    let tag = createTag(label);
    tags.appendChild(tag);
  });
};

/**
 * Send 'SUSPICIOUS' vote request to the API for the current media
 */
function sendNegativeVote() { 
  sendVote(false);
};

/**
 * Send 'TRUSTWORTHY' vote request to the API for the current media
 */
function sendPositiveVote() { 
  sendVote(true);
};

/**
 * Send vote request to the API for the current media
 * @param {boolean} value - Value of the vote
 */
function sendVote(value) { 
  api.put(
    getUrlForMediaVote(currentMedia), 
    {
      "trustworthy": value
    }
  );
};

/**
 * Style a given element as a tag
 * @param {HTMLElement} el - HTML element 
 * @returns {HTMLElement} - The same element with Tag properties
 */
function styleElementAsTag(el) {
  el.classList.add('badge');
  el.classList.add('bg-secondary');
  return el;
};


/**
 * Load the extension's display
 */
attachListenersToVoteButtons();
await populateContent();
