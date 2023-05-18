package com.fundfun.fundfund.service.portfolio;

import com.fundfun.fundfund.domain.portfolio.Portfolio;
import com.fundfun.fundfund.domain.post.Post;
import com.fundfun.fundfund.domain.user.Users;
import com.fundfun.fundfund.domain.vote.Vote;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PortfolioService {

    //전체 포트폴리오 조회
    List<Portfolio> selectAll();

    //제목으로 포트폴리오 조회
    //List<Portfolio> selectPortfolioByTitle(String title);

    //포폴id로 포트폴리오 조회
    Portfolio selectPortById(UUID portfolioId);

    //보트 id로 포폴조회
    Portfolio selectPortByVoteId(UUID voteId);

    //유저 id로 포트폴리오 조회
    Portfolio selectPortByUserId(UUID UserId);
    
    //위험도로 포트폴리오 조회
    //List<Portfolio> selectPortfolioByWarnLevel(String warnLevel);
    
    //예상수익율로 포트폴리오 조회
    //List<Portfolio> selectPortfolioByBeneRatio(Integer beneRatio);

    //포트폴리오 생성

    //포트폴리오 삭제
    void delete(Portfolio portfolio);

    //포트폴리오 수정
    void updatePort(Portfolio portfolio);

    void createPort(Post post, Users user, Vote vote);
}
