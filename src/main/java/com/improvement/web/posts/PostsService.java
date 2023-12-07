package com.improvement.web.posts;

import com.improvement.domain.posts.Posts;
import com.improvement.domain.posts.PostsRepository;
import com.improvement.web.posts.dto.PostsListResponseDto;
import com.improvement.web.posts.dto.PostsResponseDto;
import com.improvement.web.posts.dto.PostsSaveRequestDto;
import com.improvement.web.posts.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){

        List<PostsListResponseDto> collect = postsRepository.findAllByOrderByIdDesc()
                .stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        return collect;
    }

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        return new PostsResponseDto(postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("인자 없음")));
    }


    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("인자 없음"));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return posts.getId();
    }



}




