/*
 * Imports
 */
import { config } from './config.js';

/*
 * Media variables to be fetched
 */
const media = new Request(
  getApiMediaUrlFor(config.mediaId),
  {
    method: 'GET'
  }
);
const mediaScore = 0;
const mediaName = 'Default Media Name';
const mediaTags = ['News', 'World', 'Reporting'];

/*
 * Change content of HTML elements
 */
document.getElementById('mediaName').innerHTML = mediaName;

// Generate tag list
var tagList = document.getElementById('tagList');
mediaTags.forEach(function (tag) {
  var htmlTag = document.createElement('span');
  htmlTag.classList.add('badge');
  htmlTag.classList.add('bg-secondary');
  htmlTag.innerHTML = tag;
  tagList.appendChild(htmlTag);
});

/*
 * Initialize media score gauge
 */
var scoreGauge = Gauge(
  document.getElementById('scoreGauge'),
  {
    min: 0,
    max: 100,
    value: mediaScore,
    label: function(val) {return val + '%';}
  }
);

/*
 * Send media vote
 */
function sendMediaVote(vote) {
  const voteRequest = new Request(
    getApiMediaVotesUrlFor(config.mediaId),
    {
      method: 'PUT',
      body: '{}'
    }
  );
}

/*
 * API routes functions
 */
function getApiMediaUrlFor(mediaId) {
  return config.apiUrl + 'medias/' + mediaId;
}

function getApiMediaVotesUrlFor(mediaId) {
  return config.apiUrl + 'medias/' + mediaId + '/votes';
}
