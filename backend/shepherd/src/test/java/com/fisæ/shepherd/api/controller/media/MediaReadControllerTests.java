package com.fisæ.shepherd.api.controller.media;

import com.fisæ.shepherd.api.controller.ControllerTests;
import com.fisæ.shepherd.api.controller.RestPageImpl;
import com.fisæ.shepherd.application.media.contracts.MediaDto;
import com.fisæ.shepherd.application.media.query.GetMediaQuery;
import com.fisæ.shepherd.application.media.query.GetMediasQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

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
    public void givenAnIdThatDoesNotBelongToAMedia_WhenQueryingIt_ThenANotFoundShouldBeReturned()
            throws URISyntaxException {
        GetMediaQuery query = new GetMediaQuery();
        query.setId(Long.MAX_VALUE);

        String getPaginatedMediasUri = getUriForRoute("/api/medias") + "/" + query.getId();

        Assertions.assertThrows(
                HttpClientErrorException.NotFound.class,
                () -> restTemplate.getForEntity(getPaginatedMediasUri, RestPageImpl.class));
    }

    @ParameterizedTest
    @ValueSource(longs = { -1L, 0L })
    public void givenAnInvalidMediaId_WhenQueryingIt_ThenABadRequestShouldBeReturned(Long id)
            throws URISyntaxException {
        GetMediaQuery query = new GetMediaQuery();
        query.setId(id);

        String getPaginatedMediasUri = getUriForRoute("/api/medias") + "/" + query.getId();

        Assertions.assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.getForEntity(getPaginatedMediasUri, RestPageImpl.class));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1 })
    public void givenAnInvalidPageId_WhenCallingMediasGet_ThenABadRequestShouldBeReturned(int pageId)
            throws URISyntaxException {
        GetMediasQuery query = new GetMediasQuery();
        query.setPageId(pageId);

        String getPaginatedMediasUri = getUriForRoute("/api/medias") + asQueryParam(query);

        Assertions.assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.getForEntity(getPaginatedMediasUri, RestPageImpl.class));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    public void givenAnInvalidNumberOfItemsPerPages_WhenCallingMediasGet_ThenABadRequestShouldBeReturned(int count)
            throws URISyntaxException {
        GetMediasQuery query = new GetMediasQuery();
        query.setItemsPerPages(count);

        String getPaginatedMediasUri = getUriForRoute("/api/medias") + asQueryParam(query);

        Assertions.assertThrows(
                HttpClientErrorException.BadRequest.class,
                () -> restTemplate.getForEntity(getPaginatedMediasUri, RestPageImpl.class));
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
