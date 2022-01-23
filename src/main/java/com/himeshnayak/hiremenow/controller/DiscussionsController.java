package com.himeshnayak.hiremenow.controller;

import com.himeshnayak.hiremenow.model.PostDetails;
import com.himeshnayak.hiremenow.service.PostsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DiscussionsController {
    
    private final PostsService postsService;

	@Autowired
	public DiscussionsController(PostsService postsService) {
		this.postsService = postsService;
	}

    @GetMapping("/discuss")
    public String showDiscussionsPage(@CookieValue(name="userId", defaultValue="invalid") String userId, Model model) {

        if(!userId.equals("invalid")) {
            
            PostDetails post = new PostDetails();
            model.addAttribute("post", post);
    
            List<PostDetails> allPosts = postsService.getPosts();
            model.addAttribute("allPosts", allPosts);

        } else {
            return "redirect:/login";
        }

        return "discuss";
    }

    @PostMapping("/discuss")
    public String sendPost(@ModelAttribute(name="post") PostDetails post, @CookieValue(name="name") String name, @CookieValue(name="type") String type) {

        post.setName(name);
        post.setType(type);

        postsService.addPost(post);

        return "redirect:/discuss";
    }

}
