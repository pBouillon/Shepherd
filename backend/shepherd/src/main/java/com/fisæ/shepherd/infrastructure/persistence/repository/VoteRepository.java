package com.fisæ.shepherd.infrastructure.persistence.repository;


import com.fisæ.shepherd.domain.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to access the persisted {@link com.fisæ.shepherd.domain.entity.Vote} entities
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> { }
