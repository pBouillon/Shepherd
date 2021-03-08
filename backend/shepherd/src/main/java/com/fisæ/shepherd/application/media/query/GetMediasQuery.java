package com.fisæ.shepherd.application.media.query;

import com.fisæ.shepherd.domain.entity.Media;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * Query to get all the {@link Media} stored in Shepherd
 */
@Data
public class GetMediasQuery {

    /**
     * Maximum number of items that can be displayed in one page
     */
    public static final int ITEMS_PER_PAGES_MAX_VALUE = 50;

    /**
     * Minimum number of items that can be displayed in one page
     */
    public static final int ITEMS_PER_PAGES_MIN_VALUE = 1;

    /**
     * Minimum offset of the page to query
     */
    public static final int PAGE_ID_MIN_VALUE = 0;

    /**
     * Id of the page to be displayed
     *
     * The first page is at index 0
     */
    @Min(PAGE_ID_MIN_VALUE)
    private int pageId = 0;

    /**
     * Number of the items displayed per pages
     */
    @Range(min = ITEMS_PER_PAGES_MIN_VALUE, max = ITEMS_PER_PAGES_MAX_VALUE)
    private int itemsPerPages = 10;

}
