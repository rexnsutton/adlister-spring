package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdsController {
    @GetMapping("/ads")
    @ResponseBody
    public String postIndex() {
        return "Ads index page";
    }
    @GetMapping("/ads/{id}")
    @ResponseBody
    public String getPostId(@PathVariable long id) {
        return "Viewing Ad Id #: " + id;
    }
    @GetMapping("/ads/create")
    @ResponseBody
    public String showForm() {
        return "viewing create post form";
    }
    @PostMapping("/ads/create")
    @ResponseBody
    public String save(){
        return "posted";
    }
}
