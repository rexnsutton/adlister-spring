package com.codeup.blog.controllers;

import com.codeup.blog.daos.PostRepository;
import com.codeup.blog.daos.UsersRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostsController {
    private PostRepository postsDao;
    private UsersRepository usersDao;
    private EmailService emailService;
    public PostsController(PostRepository postRepository, UsersRepository usersRepository, EmailService emailService){
        this.postsDao = postRepository;
        this.usersDao = usersRepository;
        this.emailService = emailService;
    }


    @GetMapping("/posts")
//    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public String index(Model model){
//            Post firstPost = postsDao.findFirstByTitle("PS1");
//            System.out.println("firstPost.getId() = " + firstPost.getId());
            List<Post> postsList = postsDao.findAll();
            model.addAttribute("noPostsFound", postsList.size() == 0);
            model.addAttribute("posts", postsList);
            return "/posts/index";
        }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model){
        Post post = postsDao.getOne(id);
        model.addAttribute("postId", id);
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }


    @PostMapping("/posts/create")
    public String savePost(@ModelAttribute Post newPost) {
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newPost.setOwner(currentUser);
        Post savedPost = postsDao.save(newPost);
        emailService.prepareAndSend(savedPost, "created ad", " your ad has been created");
        return "redirect:/posts/" + savedPost.getId();
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(Model model, @PathVariable long id){
        // find an ad
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "/posts/edit";
    }
    @PostMapping("/posts/{id}/edit")
    @ResponseBody
    public String update(@PathVariable long id,
                         @RequestParam(name = "title") String title,
                         @RequestParam(name = "description") String desc){
        // find an ad
        Post foundPost = postsDao.getOne(id); // select * from ads where id = ?
        // edit the ad
        foundPost.setTitle(title);
        foundPost.setDescription(desc);
        // save the changes
        postsDao.save(foundPost); // update ads set title = ? where id = ?
        return "Post updated";
    }

    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String destroy(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }



}