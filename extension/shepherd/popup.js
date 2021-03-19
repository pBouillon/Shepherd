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
 * @const {Object} buttons - Buttons 
 */
const buttons = {
  yes: document.getElementById('buttonYes'),
  no: document.getElementById('buttonNo')
};

/**
 * Hold the current media's information
 */
let currentMedia = {};

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
function fetchMediaByWebsite(website) {
  return {
    id: 1,
    name: 'Les Inrockuptibles',
    rate: .0,
    tags: ['News', 'World', 'Reporting'],
  };
};

/**
 * @todo doc
 */
function getCurrentPageUri() {
  return window.location.protocol + '//' + window.location.host;
};

/**
 * Generate configuration dictionary for the gauge instanciation
 * @param {Object} - The media's information from which the gauge will be set up
 * @returns {Object} - The preconfigured configuration object
 */
function getGaugeConfigurationFor(media) {
  return {
    min: config.mediaRateMin,
    max: config.mediaRateMax,
    value: Math.round(media.rate),
    label: (val) => val + '%'
  }
};

/**
 * @todo doc
 */
function getUrlForMedia(media) {
  return config.apiUri + "medias/" + media.id
};

/**
 * @todo doc
 */
function getUrlForMediaVote(media) {
  return config.apiUri + "medias/" + media.id + "/votes"
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
 * Initialize listener events for the buttons
 */
function linkButtons() {
  buttons.yes.addEventListener('click', sendPositiveVote);
  buttons.no.addEventListener('click', sendNegativeVote);
};

/**
 * Populate the extension's main window
 */
function populateContent() {
  // Retrieve media's website
  let uri = getCurrentPageUri();
  currentMedia = fetchMediaByWebsite(uri);
  
  // Load title
  document.getElementById('mediaName').innerHTML = currentMedia.name;

  // Populate tags
  populateTagsFrom(currentMedia);

  // Initialize gauge
  Gauge(
    document.getElementById('rateGauge'),
    getGaugeConfigurationFor(currentMedia)
  );
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
 * @todo doc
 */
function sendNegativeVote() { 
  sendVote(false);
};

/**
 * @todo doc
 */
function sendPositiveVote() { 
  sendVote(true);
};

/**
 * @todo doc
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
populateContent();
linkButtons();
