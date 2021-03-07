package com.fisæ.shepherd.application.media.query;

import com.fisæ.shepherd.domain.entity.Media;
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
    private int pageId = 0;

    /**
     * Number of the items displayed per pages
     */
    private int itemsPerPages = 10;

}
