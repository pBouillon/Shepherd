/*
 * Constants declaration and configuration
 */
const privateConfig = {
    serverUri: "http://localtest.me",
    apiPort: 8080,
    websitePort: 4200
};

export const config = {
    apiUri: privateConfig.serverUri + ":" + privateConfig.apiPort + "/api/",
    websiteUri: privateConfig.serverUri + ":" + privateConfig.websitePort + "/",
    mediaId: 0,
    mediaRateMin: 0,
    mediaRateMax: 100,
    console: chrome.extension.getBackgroundPage().console
};
