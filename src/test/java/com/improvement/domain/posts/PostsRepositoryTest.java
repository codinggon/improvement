package com.improvement.domain.posts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @DisplayName("")
    @Test
    public void _테스트() throws Exception{

        List<Posts> allOrderByIdDesc = postsRepository.findAllByOrderByIdDesc();
        for (Posts posts : allOrderByIdDesc) {
            System.out.println("posts = " + posts);
        }

    }



}