package com.fundfun.fundfund.service.reply;

import com.fundfun.fundfund.domain.post.Post;
import com.fundfun.fundfund.domain.reply.Reply;
import com.fundfun.fundfund.domain.user.Users;
import com.fundfun.fundfund.service.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceImplTest {
    @Autowired
    PostService postService;

    @Autowired
    ReplyService replyService;

    @Test
    public void 댓글_등록(){
        List<Post> pList = postService.selectAll();
        Post post = pList.get(0);
        Users user = null;

        for(int i=0; i<3; i++){
            replyService.insertReply(post, user, "댓글 " + i);
        }

        post = pList.get(1);
        for(int i=0; i<5; i++){
            replyService.insertReply(post, user, "댓글 " + i);
        }

    }

    @Test
    public void 모든_댓글_조회(){
        List<Reply> list = replyService.selectAll();
        for(Reply r : list){
            System.out.println("id = " + r.getId() + ", 내용 = " + r.getContentReply());
        }
    }

    @Test
    public void 댓글_수정(){
        UUID replyId = UUID.fromString("43c76c98-8dfb-452c-b590-7c99ee719325");
        System.out.println("replyId = " + replyId);
        Reply reply = replyService.selectById(replyId);
        reply.setContentReply("수정된 댓글!");
        replyService.updateReply(reply);
    }

    @Test
    public void 댓글_삭제(){
        List<Reply> list = replyService.selectAll();
        Reply reply = list.get(5);
        replyService.deleteReply(reply.getId());
    }

    @Test
    public void 댓글_수_조회(){
        List<Reply> list = replyService.selectAll();
        Reply reply = list.get(3);
        UUID postId = reply.getPost().getId();

        System.out.println("댓글 수 = " + replyService.countByPostId(postId));
    }
    
    @Test
    public void 댓글_최신순_조회(){
        List<Reply> list = replyService.selectAll();
        Reply reply = list.get(3);
        UUID postId = reply.getPost().getId();

        List<Reply> rList = replyService.selectReplyByPostId(postId);
        for(Reply r : rList){
            System.out.println("id = " + r.getId() + ", 내용 = " + r.getContentReply() + ", 작성일 = " + r.getCreatedAt());
        }
    }
}