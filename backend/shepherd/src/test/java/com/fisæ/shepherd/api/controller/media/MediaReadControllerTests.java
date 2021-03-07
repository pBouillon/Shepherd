package com.fisæ.shepherd.api.controller.media;

import com.fisæ.shepherd.api.controller.ControllerTests;
import com.fisæ.shepherd.api.controller.RestPageImpl;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.query.GetMediasQuery;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test suite for {@link MediaReadController}
 */
public class MediaReadControllerTests extends ControllerTests {

    /**
     * Convert the provided query to its query param representation
     *
     * @param query GetMedias query
     *
     * @return The string representation of the query parameters as query params
     */
    public static String asQueryParam(GetMediasQuery query) {
        return "?itemsPerPages=" + query.getItemsPerPages()
                + "&pageId=" + query.getPageId();
    }

    @Test
    public void givenTheDefaultGetMediasQuery_WhenCallingMediasGet_ThenTheMediasShouldBeReturned()
            throws URISyntaxException {
        GetMediasQuery query = new GetMediasQuery();

        String getPaginatedMediasUri = getUriForRoute("/api/medias") + asQueryParam(query);

        ResponseEntity<RestPageImpl<MediaDto>> mediasResponse = restTemplate.exchange(
                getPaginatedMediasUri, HttpMethod.GET, null, new ParameterizedTypeReference<>() { });

        assertEquals(HttpStatus.OK, mediasResponse.getStatusCode());
    }

}
