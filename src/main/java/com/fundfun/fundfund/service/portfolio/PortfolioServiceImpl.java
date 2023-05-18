package com.fundfun.fundfund.service.portfolio;

import com.fundfun.fundfund.domain.portfolio.Portfolio;
import com.fundfun.fundfund.domain.post.Post;
import com.fundfun.fundfund.domain.user.Users;
import com.fundfun.fundfund.domain.vote.Vote;
import com.fundfun.fundfund.repository.portfolio.PortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
    public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private final PortRepository portRep;


    public void createPort(Post post, Users user, Vote vote, String title, String contentPortfolio, float beneRatio, String warnLevel){
        Portfolio port = new Portfolio();

        port.setTitle(title);
        port.setContentPortfolio(contentPortfolio);
        port.setWarnLevel(warnLevel);
        port.setBeneRatio(beneRatio);

        port.linkPost(post);
        port.linkUsers(user);
        port.linkVote(vote);

        portRep.save(port);
    }

    //전체 포폴조회
    public List<Portfolio> selectAll() {
        return portRep.findAll();
    }

    //포트폴리오 아이디로 포폴조회
    public Portfolio selectPortById(UUID portfolioId){
        return portRep.findById(portfolioId).orElse(null);
    }

    //보트 아이디로 포폴조회
    public Portfolio selectPortByVoteId(UUID voteId) {
        Portfolio port = portRep.findByVoteId(voteId);
        if(port  == null)
            throw new RuntimeException("해당 포폴이 존재하지 않습니다.");
        return port ;
    }


    //유저 id으로 포폴조회
    public Portfolio selectPortByUserId(UUID userId) {
        Portfolio port = portRep.findByUserId(userId);
        if(port  == null)
            throw new RuntimeException("해당 포폴이 존재하지 않습니다.");
        return port ;
    }



    //위험도로 포트폴리오 조회
    //public List<Portfolio> selectPortfolioByWarnLevel(String warnLevel){

    //     return portRep.findByWarnLevel(warnLevel);
    //}

    //예상수익율로 포트폴리오 조회
    //public List<Portfolio> selectPortfolioByBeneRatio(Integer beneratio){
    //    return portRep.findByBeneRatio(beneratio);
    //}

    //포트폴리오 삭제
    public void delete(Portfolio portfolio){
        portRep.delete(portfolio);
    }

    //포트폴리오 수정
    public void updatePort(Portfolio portfolio){
        Portfolio existingPort = portRep.findById(portfolio.getId()).orElse(null);
        if (existingPort != null) {
            // 변경할 필드값을 업데이트합니다.
            existingPort.setTitle(portfolio.getTitle());
            existingPort.setContentPortfolio(portfolio.getContentPortfolio());
            existingPort.setWarnLevel(portfolio.getWarnLevel());
            existingPort.setBeneRatio(portfolio.getBeneRatio());

            // 게시물을 저장하여 업데이트합니다.
            portRep.save(existingPort);

        }else{
            throw new RuntimeException("검색한 게시물이 존재하지 않습니다.");
        }
    }


}


