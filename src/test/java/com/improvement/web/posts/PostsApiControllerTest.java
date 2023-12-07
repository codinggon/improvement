package com.improvement.web.posts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.improvement.domain.posts.Posts;
import com.improvement.domain.posts.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostsApiControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    ObjectMapper objectMapper;


    @DisplayName("")
    @Test
    public void Posts_저장() throws Exception{

        LocalDateTime now = LocalDateTime.of(2022, 1, 1, 1, 1, 1);

        Posts posts = Posts.builder()
                .title("title...")
                .content("content...")
                .author("author...")
                .build();

        mockMvc.perform(post("/api/v1/posts")
                .content(objectMapper.writeValueAsString(posts))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());

//        Assertions.assertThat(posts.getCreateDate()).isAfter(now);

    }

    @DisplayName("")
    @Test
    public void Posts_수정() throws Exception{

        Posts posts = Posts.builder()
                .title("title...")
                .content("content...")
                .author("author...")
                .build();

        Posts savedPosts = postsRepository.save(posts);

        Posts posts2 = Posts.builder()
                .title("title...222")
                .content("content...222")
                .build();


        mockMvc.perform(put("/api/v1/posts/" + savedPosts.getId())
                .content(objectMapper.writeValueAsString(posts2))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());

        Posts result = postsRepository.findById(savedPosts.getId()).get();


        Assertions.assertThat(posts2.getTitle()).isEqualTo(result.getTitle());
        Assertions.assertThat(posts2.getContent()).isEqualTo(result.getContent());

    }




}