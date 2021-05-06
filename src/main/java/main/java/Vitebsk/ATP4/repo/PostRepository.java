package main.java.Vitebsk.ATP4.repo;

import org.springframework.data.repository.CrudRepository;

import main.java.Vitebsk.ATP4.models.Post;

public interface PostRepository extends CrudRepository<Post, Long>
{
    
}
