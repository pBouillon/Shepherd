package com.fisæ.shepherd.infrastructure.persistence.repository;

import com.fisæ.shepherd.domain.entities.Media;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to access the persisted {@link Media} entities
 */
@Repository
public interface MediaRepository extends PagingAndSortingRepository<Media, Long> {
}
