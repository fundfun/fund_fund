package com.fundfun.fundfund.controller.post;

import com.fundfun.fundfund.domain.post.Post;
import com.fundfun.fundfund.service.post.PostService;
import com.fundfun.fundfund.service.post.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IdeaController {

    private final PostService postService;

    /**
     * 아이디어 전체조회 화면 이동
     * */
    @GetMapping("/idea/ideaList")
    public String goIdeaList() {
        return "idea/ideaList";
    }

    /**
     * 아이디어 상세조회 화면 이동
     * */
    @GetMapping("/idea/ideaDetail")
    public String goIdeaDetail() {
        return "idea/ideaDetail";
    }

    /**
     * 아이디어 작성 및 수정 화면 이동
     * */
    @GetMapping("/idea/ideaWrite")
    public String goIdeaWrite() {
        return "idea/ideaWrite";
    }

    @GetMapping("/idea/new")
    public String createForm(Model model) {
        model.addAttribute("form", new PostForm());
        return "idea/createPostForm";
    }

    @PostMapping("/idea/new")
    public String create(PostForm form) {

        Post post = new Post();
        post.setTitle(form.getTitle());
        post.setContentPost(form.getContentPost());
        postService.createPost(post);
        return "redirect:/idea";
    }

}





