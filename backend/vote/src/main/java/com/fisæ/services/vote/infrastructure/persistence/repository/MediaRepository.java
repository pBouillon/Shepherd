package com.fisæ.services.vote.infrastructure.persistence.repository;

import com.fisæ.services.vote.domain.entity.Media;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to access the persisted {@link Media} entities
 */
@Repository
public interface MediaRepository extends PagingAndSortingRepository<Media, Long> {
}
