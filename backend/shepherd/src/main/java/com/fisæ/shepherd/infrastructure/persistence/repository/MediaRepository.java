package com.fisæ.shepherd.infrastructure.persistence.repository;

import com.fisæ.shepherd.domain.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to access the persisted {@link Media} entities
 */
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
}
