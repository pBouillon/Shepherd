package com.fisæ.services.vote.infrastructure.persistence.repository;

import com.fisæ.services.vote.domain.entity.TrustReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository to access the persisted {@link TrustReport} entities
 */
@Repository
public interface TrustReportRepository extends JpaRepository<TrustReport, Long> {

    /**
     * TODO
     *
     * @param mediaId
     * @return
     */
    Optional<TrustReport> findByMediaId(Long mediaId);

}
