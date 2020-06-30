package com.codeup.blog.controllers;

import com.codeup.blog.daos.PostRepository;
import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostsController {
    private PostRepository postsDao;
    public PostsController(PostRepository postRepository){
        postsDao = postRepository;
    }

    @GetMapping("/posts")
//    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public String index(Model model){
        ArrayList<Post> PostsList = new ArrayList<>();
//        PostsList.add(new Post("PS1", "Used"));
        PostsList.add(new Post("PS2", "Used"));
//        PostsList.add(new Post("PS4", "Used"));
//        PostsList.add(new Post("SNES", "Used"));
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

    @PostMapping("/posts/create")
    @ResponseBody
    public String save(){

            Post newAd = new Post("XBOX X","brand new");
            postsDao.save(newAd);
            return "Post created";
        }

    @PutMapping("/posts/{id}/edit")
    @ResponseBody
    public String update(@PathVariable long id){
        // find an ad
        Post foundPost = postsDao.getOne(id); // select * from ads where id = ?
        // edit the ad
        foundPost.setTitle("XBOX Series X");
        foundPost.setDescription("holiday");
        // save the changes
        postsDao.save(foundPost); // update ads set title = ? where id = ?
        return "Post updated";
    }

    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String destroy(@PathVariable long id){
        postsDao.deleteById(id);
        return "posts/show";
    }

}