/*
 * Media variables to be fetched
 */
var mediaId = 0;
const apiUrl = "http://localhost:4200/api";
const media = new Request(
  apiUrl + "/medias/" + mediaId,
  {
    method: 'GET'
  }
);
const mediaScore = 0;
const mediaName = "Default Media Name";
const mediaTags = ["News", "World", "Reporting"];

/*
 * Change content of HTML elements
 */
document.getElementById("mediaName").innerHTML = mediaName;

// Generate tag list
var tagList = document.getElementById("tagList");
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
  document.getElementById("scoreGauge"),
  {
    min: 0,
    max: 100,
    value: mediaScore,
    label: function(val) {return val + "%";}
  }
);

/*
 * Send media vote
 */
function sendMediaVote(vote) {
  const voteRequest = new Request(
    apiUrl + "/medias/" + mediaId + "/votes",
    {
      method: 'PUT',
      body: '{}'
    }
  );
}
