/*
 * Constants declaration and configuration
 */
const privateConfig = {
    serverUri: "http://localtest.me",
    shepherdUri: "http://localhost",
    apiPort: 8080,
    websitePort: 4200
};

export const config = {
    apiUri:      privateConfig.serverUri + ":" + privateConfig.apiPort + "/api/",
    shepherdUri: privateConfig.shepherdUri + ":" + privateConfig.websitePort + "/",
    mediaId: 0,
    mediaRateMin: 0,
    mediaRateMax: 100,
    bgPage: chrome.extension.getBackgroundPage()
};
