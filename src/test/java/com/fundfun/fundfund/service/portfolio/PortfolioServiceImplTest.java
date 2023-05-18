package com.fundfun.fundfund.service.portfolio;

import com.fundfun.fundfund.domain.portfolio.Portfolio;
import com.fundfun.fundfund.domain.post.Post;
import com.fundfun.fundfund.domain.user.Users;
import com.fundfun.fundfund.domain.vote.Vote;
import com.fundfun.fundfund.service.portfolio.PortfolioService;
import com.fundfun.fundfund.service.post.PostService;
import com.fundfun.fundfund.service.vote.VoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class PortfolioServiceImplTest {
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    PostService postService;
    @Autowired
    VoteService voteService;

    @Test
    public void 포폴생성() throws Exception {
 //       Users user = new Users();
 //       usersRepository.save(user);
        List<Post> list = postService.selectAll();
        Post post = list.get(6);
        Vote vote = voteService.selectVoteByPostId(post.getId());

        portfolioService.createPort(post, null, vote);

        List<Portfolio> port= portfolioService.selectAll();
        for(Portfolio p : port){
            System.out.println(/*user.getId()+*/"님이"+vote.getId()+"에 투표하심");
    }
        /*for (int i = 0; i < 10; i++) {
            Portfolio port = Portfolio.builder()
                    .title("title" + i)
                    .ContentPortfolio(null)
                    .warnLevel(null)
                    .beneRatio(i)
                    .build();
            portfolioService.createPort(port);
        }*/
    }
    @Test
    public void 포폴전체조회() throws Exception {
        List<Portfolio> list = portfolioService.selectAll();
        for(Portfolio port : list) System.out.println(port);
    }


    @Test
    public void 포폴아이디로조회()throws Exception {
        List<Portfolio> list = portfolioService.selectAll();
        UUID uuid= list.get(0).getId();
        Portfolio p = portfolioService.selectPortById(uuid);
        System.out.println(p);
    }//

    @Test
    public void 보트아이디조회()throws Exception {
        List<Portfolio> list = portfolioService.selectAll();
        UUID uuid= list.get(10).getId();
        Portfolio p = portfolioService.selectPortByVoteId(uuid);
        System.out.println(uuid);
        System.out.println(p);
    }//

    @Test
    public void 유저아이디조회()throws Exception {
        Users user = new Users();
        List<Portfolio> list = portfolioService.selectAll();
        UUID uuid= list.get(6).getId();
        Portfolio p = portfolioService.selectPortByUserId(uuid);
        System.out.println(p);
    }//

   /* @Test
    public void 제목_포폴조회() throws Exception {
        List<Portfolio> list = portfolioService.selectPortfolioByKeyword(null);
        for(Portfolio port : list) System.out.println(port);
    }

    @Test
    public void 작성자_포폴조회() throws Exception {
        Optional<Portfolio> olist = portfolioService.selectPortfolioByUserId(UUID.randomUUID());
        olist.ifPresent(portfolio -> System.out.println(portfolio));

    }

    @Test
    public void 위험도_포폴조회() throws Exception {
        List<Portfolio> list = portfolioService.selectPortfolioByWarnLevel(null);
        for(Portfolio port : list) System.out.println(port);
    }

    @Test
    public void 예상수익률_포폴조회() throws Exception {
        List<Portfolio> list = portfolioService.selectPortfolioByBeneRatio(1);
        for(Portfolio port : list) System.out.println(port);
    }

    /*
    @Test
    public void 게시물삭제() throws Exception {

        UUID uuid = portfolioService.selectAll().get(0).getId();
        Optional<Post> postToDelete = portfolioService.selectPortfolioByUserId(uuid);

        // 게시물이 존재하는지 확인
        assertTrue(postToDelete.isPresent());

        // 게시물 삭제
        postToDelete.ifPresent(post -> {
            portfolioServiceService.delete(post);
            assertFalse(portfolioService.selectPostByUserId(1).isPresent());
        })
    };*/

}