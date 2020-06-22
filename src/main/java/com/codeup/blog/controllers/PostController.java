package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String postIndex() {
        return "Posts index page";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getPostId(@PathVariable long id) {
        return "Viewing Post Id #: " + id;
    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreateForm() {
        return "viewing create post form";
    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String insert(){
        return "posted";
    }
}
