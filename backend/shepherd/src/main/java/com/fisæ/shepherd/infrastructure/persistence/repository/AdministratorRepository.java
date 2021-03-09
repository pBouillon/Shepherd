package com.fisæ.shepherd.infrastructure.persistence.repository;

import com.fisæ.shepherd.domain.entity.Administrator;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to access the persisted {@link Administrator} entities
 */
@Repository
public interface AdministratorRepository extends PagingAndSortingRepository<Administrator, Long> {
}
