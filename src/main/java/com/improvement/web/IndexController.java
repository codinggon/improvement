package com.improvement.web;

import com.improvement.domain.posts.Posts;
import com.improvement.web.posts.PostsService;
import com.improvement.web.posts.dto.PostsListResponseDto;
import com.improvement.web.posts.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        List<PostsListResponseDto> allDesc = postsService.findAllDesc();
        model.addAttribute("posts", allDesc);
        return  "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return  "posts/posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto posts = postsService.findById(id);
        model.addAttribute("posts",posts);
        return  "posts/posts-update";
    }






}
