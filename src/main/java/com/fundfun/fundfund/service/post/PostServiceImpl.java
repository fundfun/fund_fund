package com.fundfun.fundfund.service.post;

import com.fundfun.fundfund.domain.post.Post;
import com.fundfun.fundfund.domain.post.StPost;
import com.fundfun.fundfund.domain.vote.Vote;
import com.fundfun.fundfund.dto.post.PostDto;
import com.fundfun.fundfund.dto.post.PostRequestDto;
import com.fundfun.fundfund.repository.post.PostRepository;
import com.fundfun.fundfund.repository.vote.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final VoteRepository voteRepository;
    private final ModelMapper modelMapper;


    public void createPost(PostRequestDto postRequestDTO) {
        Post post = modelMapper.map(postRequestDTO, Post.class);
        postRepository.save(post);
    }

    @Override
    public List<PostDto> selectAll() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<PostDto> selectPostByKeyword(String keyword) {
        List<Post> posts = postRepository.findByTitleContaining(keyword);
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PostDto> selectPostByUserId(UUID userId) {
        Optional<Post> post = postRepository.findById(userId);
        return post.map(p -> modelMapper.map(p, PostDto.class));
    }

    @Override
    public List<PostDto> selectPostByStatus(StPost status) {
        List<Post> posts = postRepository.findByStatusPost(status);
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> selectPostByCategory(String category) {
        List<Post> posts = postRepository.findByCategoryPost(category);
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(Post post) {

    }

    @Override
    public void delete(PostDto post) {

    }

    @Override
    public void updatePost(PostDto post) {

    }


    public void delete(UUID postId) {
        postRepository.deleteById(postId);
    }

    public void updatePost(UUID postId, PostRequestDto postRequestDto) {
        Post existingPost = postRepository.findById(postId).orElse(null);
        if (existingPost != null) {
            modelMapper.map(postRequestDto, existingPost);
            postRepository.save(existingPost);
        } else {
            throw new RuntimeException("게시물이 존재하지 않습니다.");
        }
    }

    @Override
    public List<PostDto> getPostsOrderByLikes() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "likePost"));
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addLike(UUID postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시물이 존재하지 않습니다."));

        post.setLikePost(post.getLikePost() + 1); // 좋아요 수 증가
        postRepository.save(post);

        updatePostStatus(post); // 좋아요 개수 확인 및 상태 업데이트
    }

    @Override
    public void updateStatus(PostDto post, StPost status) {
        post.setStatusPost(status);
        Vote vote = new Vote();
        vote.linkPost(post);
        voteRepository.save(vote);
        post.linkVote(vote);
        postRepository.save(post);
    }

    @Override
    public void updatePostStatus(PostDto post) {

    }

    @Override
    public int getLikeById(UUID postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("게시물이 존재하지 않습니다."));
        return post.getLikePost();
    }
}