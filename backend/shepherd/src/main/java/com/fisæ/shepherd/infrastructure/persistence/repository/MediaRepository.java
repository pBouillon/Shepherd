package com.fisæ.shepherd.infrastructure.persistence.repository;

import com.fisæ.shepherd.domain.entity.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.net.URI;

/**
 * Repository to access the persisted {@link Media} entities
 */
@Repository
public interface MediaRepository extends PagingAndSortingRepository<Media, Long> {

    /**
     * Get whether or not a media exists with the same name (case insensitive)
     *
     * @param name Media name
     *
     * @return True if any other media has the same name; false otherwise
     */
    boolean existsByNameIgnoreCase(String name);

    /**
     * Get a paginated list of all medias using a specific name
     *
     * @param name Media name
     * @param request The pageable request to paginate the results
     *
     * @return A list of all medias using this name
     */
    Page<Media> findAllByNameContainingIgnoreCase(String name, Pageable request);

    /**
     * Get a paginated list of all medias using a specific name and website
     *
     * @param name Media name
     * @param website URL of the media's website
     * @param request The pageable request to paginate the results
     *
     * @return A list of all medias using this name and this website
     */
    Page<Media> findAllByNameContainingIgnoreCaseAndWebsite(String name, URI website, Pageable request);

    /**
     * Get a paginated list of all medias using a specific website
     *
     * @param website URL of the media's website
     * @param request The pageable request to paginate the results
     *
     * @return A list of all medias using this website
     */
    Page<Media> findAllByWebsite(URI website, Pageable request);

}
