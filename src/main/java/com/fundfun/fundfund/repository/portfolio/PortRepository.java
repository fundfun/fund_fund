package com.fundfun.fundfund.repository.portfolio;

import com.fundfun.fundfund.domain.portfolio.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PortRepository extends JpaRepository<Portfolio, UUID> {

   // List<Portfolio> findByWarnLevel(String warnLevel);


    // List<Portfolio> findByBeneRatio(int beneratio);

    List<Portfolio> findByTitle(String title);

    Portfolio findByVoteId(UUID voteId);

    Portfolio findByUserId(UUID userId);
}