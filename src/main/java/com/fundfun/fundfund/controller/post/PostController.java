package com.fundfun.fundfund.controller.post;

import com.fundfun.fundfund.domain.post.Post;
import com.fundfun.fundfund.service.post.PostService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class PostController {
    @Autowired
    PostService postService;
    @RequestMapping("/")
    public ModelAndView post() {
        List<Post> postList = postService.selectAll();
        Post p1 = postList.get(0);
            ModelAndView mav = new ModelAndView("post/post");
            mav.addObject("title", p1.getTitle());
        return mav;
    }
    @RequestMapping("/post")
    public ModelAndView postInfo() {
        List<Post> postList = postService.selectAll();


        ModelAndView mav = new ModelAndView("order/order_receipt");
        mav.addObject("curCollect", postList.size());


        return mav;
    }
}
