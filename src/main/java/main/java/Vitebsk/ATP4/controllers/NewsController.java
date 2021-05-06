package main.java.Vitebsk.ATP4.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.java.Vitebsk.ATP4.models.Post;
import main.java.Vitebsk.ATP4.repo.PostRepository;


@Controller
public class NewsController 
{
  @Autowired
  private PostRepository postRepository;

    @GetMapping("/news")
    public String news(Model model)
    {
      Iterable<Post> posts = postRepository.findAll();
      model.addAttribute("posts", posts);
		  return "news";   
    }

    @GetMapping("/addNews")
    public String newsAdd(Model model)
    {
      
		  return "/addNews";   
    }

    @PostMapping("/addNews")
    public String newsPostAdd(@RequestParam String full_txt, @RequestParam String anons, @RequestParam String title, Model model)
    {
      Post post = new Post(title, full_txt, anons);
      postRepository.save(post);
      return "redirect:/news";
    }

    @GetMapping("/news/{id}")
    public String newsDetails(@PathVariable(value = "id") long id, Model model)
    {
      if(!postRepository.existsById(id))
      {
        return "redirect:/news";
      }
      Optional<Post> post = postRepository.findById(id);
      ArrayList <Post> res = new ArrayList<>();
      post.ifPresent(res::add);
      model.addAttribute("post", res);
		  return "news-details";    
    }

    @GetMapping("/news/{id}/edit")
    public String newsEdit(@PathVariable(value = "id") long id, Model model)
    {
      if(!postRepository.existsById(id))
      {
        return "redirect:/news";
      }
      Optional<Post> post = postRepository.findById(id);
      ArrayList <Post> res = new ArrayList<>();
      post.ifPresent(res::add);
      model.addAttribute("post", res);
		  return "news-edit";    
    }
    
    @PostMapping("/news/{id}/edit")
    public String newsPostUpdate(@PathVariable(value = "id") long id, @RequestParam String full_txt, @RequestParam String anons, @RequestParam String title, Model model)
    {
      Post post = postRepository.findById(id).orElseThrow();
      post.setTitle(title);
      post.setAnons(anons);
      post.setFull_txt(full_txt);
      postRepository.save(post);
      return "redirect:/news";
    }

    @PostMapping("/news/{id_post}/remove")
    public String newsPostDell(@PathVariable(value = "id_post") long id_post, Model model)
    {
      Post post = postRepository.findById(id_post).orElseThrow();
      postRepository.delete(post);
      return "redirect:/news";
    }

}
