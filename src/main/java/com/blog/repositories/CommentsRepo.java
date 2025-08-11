package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Comments;
import com.blog.entities.Post;

public interface CommentsRepo extends JpaRepository<Comments, Long> {

    // Custom method to find all comments for a given post, ordered by creation date.
	List<Comments> findByPostOrderByCreatedAtAsc(Post post);

}
