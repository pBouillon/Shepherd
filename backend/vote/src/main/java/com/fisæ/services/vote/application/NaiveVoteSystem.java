package com.fisæ.services.vote.application;

import com.fisæ.services.vote.domain.entity.Media;
import com.fisæ.services.vote.domain.entity.TrustReport;
import com.fisæ.services.vote.domain.message.VoteMessage;
import com.fisæ.services.vote.infrastructure.persistence.repository.TrustReportRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Define a naive system for handling the votes
 *
 * Simply increase or decrease the overall rating of a media according to the vote received
 */
@Log4j2
@Service
public class NaiveVoteSystem implements VoteHandler {

    /**
     * DAO to access the persisted {@link Media} entities
     */
    private final TrustReportRepository repository;

    @Autowired
    public NaiveVoteSystem(TrustReportRepository mediaRepository) {
        repository = mediaRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(VoteMessage vote) {
        Optional<TrustReport> entity = repository.findByMediaId(vote.targetedMediaId);

        if (entity.isEmpty()) {
            log.warn("No report found for the provided id '{}', ignoring the vote", vote.targetedMediaId);
            return;
        }

        TrustReport report = entity.get();
        float currentRate = report.getRate();

        float newRate = report.getRate();
        report.setRate(vote.trustworthy
            ? ++newRate
            : --newRate);

        repository.save(report);

        log.info("Vote acknowledged for {} (previous rate was: {})", report, currentRate);
    }

}
