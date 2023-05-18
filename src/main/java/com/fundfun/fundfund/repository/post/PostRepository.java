package com.fundfun.fundfund.repository.post;

import com.fundfun.fundfund.domain.post.Post;
import com.fundfun.fundfund.domain.post.StPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    //키워드가 포함된 제목으로 찾기
    List<Post> findByTitleContaining(String keyword);

    //상태로 찾기
    @Query(value="select p from Post p where p.statusPost = ?1 order by p.createdAt")
    List<Post> findByStatusPost(StPost status);

    //카테고리로 찾기
    List<Post> findByCategoryPost(String category);

    Post findByTitle(String title);
}