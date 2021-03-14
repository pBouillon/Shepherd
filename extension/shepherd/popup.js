import { config } from './config.js';

/**
 * @const {number} MEDIA_NAME_MAX_LENGTH - Maximum length for media name
 */
const MEDIA_NAME_MAX_LENGTH = 15;

/**
 * Hold the current media's information
 */
let media = { };

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
}

/**
 * Retrieve the information related to the current media from the API
 * @param {string} website - The URL of the website currently displayed
 * @returns {Object} - The media details
 */
function fetchMediaByWebsite(website) {
  return {
    name: 'Les Inrockuptibles',
    rate: .0,
    tags: ['News', 'World', 'Reporting'],
  };
}

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
 * Get the trimmed version of a long string if needed
 * The string will remain untouched if there is no need to trim it
 * @param {string} value - String to be trimmed
 * @returns {string} - Trimmed string
 */
function getTrimmed(value) {
  return value.length <= MEDIA_NAME_MAX_LENGTH
    ? value
    : value.substring(0, MEDIA_NAME_MAX_LENGTH) + '...';
}

/**
 * Populate the extension's main window
 */
function populateContent() {
  // Retrieve media's website
  let uri = window.location.protocol + '//' + window.location.host;
  media = fetchMediaByWebsite(uri);
  
  // Load title
  document.getElementById('mediaName').innerHTML = media.name;

  // Populate tags
  populateTagsFrom(media);

  // Initialize gauge
  Gauge(
    document.getElementById('rateGauge'),
    getGaugeConfigurationFor(media));
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
}

/**
 * Style a given element as a tag
 * @param {HTMLElement} el - HTML element 
 * @returns {HTMLElement} - The same element with Tag properties
 */
function styleElementAsTag(el) {
  el.classList.add('badge');
  el.classList.add('bg-secondary');
  return el;
}

/**
 * Placeholder - To be implemented later
 */
function vote() { }

/**
 * Load the extension's display
 */
populateContent();
