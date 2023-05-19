package com.fundfun.fundfund.dto.portfolio;

import com.fundfun.fundfund.domain.portfolio.Portfolio;
import com.fundfun.fundfund.domain.post.Post;
import com.fundfun.fundfund.domain.user.Users;
import com.fundfun.fundfund.domain.vote.Vote;

import java.util.UUID;

public class PortfolioDto extends Portfolio {

    private UUID portfolioId;
    private UUID voteId;
    private UUID postId;
    private String title;
    private String ContentPortfolio;
    private String warnLevel;
    private float beneRatio;
}


