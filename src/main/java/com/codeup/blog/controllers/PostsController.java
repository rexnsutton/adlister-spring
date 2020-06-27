package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostsController {

    @GetMapping("/posts")
//    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public String index(Model model){
        ArrayList<Post> PostsList = new ArrayList<>();
        PostsList.add(new Post("PS1", "Used"));
        PostsList.add(new Post("PS2", "Used"));
        PostsList.add(new Post("PS4", "Used"));
        PostsList.add(new Post("SNES", "Used"));
        model.addAttribute("noPostsFound", PostsList.size() == 0);
        model.addAttribute("posts", PostsList);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable long id , Model model){
        model.addAttribute("PostId", id);
        model.addAttribute("post", new Post("PS1", "cool bro"));
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String showForm(){
        return "view the form for creating an ad";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String save(){
        return "create a new ad";
    }


}