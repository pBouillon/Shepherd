package com.fisæ.shepherd.application.media.query;

import com.fisæ.shepherd.domain.entities.Media;
import lombok.Data;

/**
 * Query to get all the {@link Media} stored in Shepherd
 */
@Data
public class GetMediasQuery {

    /**
     * Id of the page to be displayed
     *
     * The first page is at index 0
     */
    public int pageId = 0;

    /**
     * Number of the items displayed per pages
     */
    public int itemsPerPages = 10;

}
