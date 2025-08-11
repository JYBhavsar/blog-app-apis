package com.blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Likes;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface LikesRepo extends JpaRepository<Likes, Long> {
	
	// Method to count the number of likes for a specific post
	Long countByPost(Post post);
	
	// Methods to check if a user has already liked a post
	Optional<Likes> findByPostAndUser(Post post, User user);
	
	// A method to get all likes for a post, useful for displaying who liked it
	List<Likes> findByPost(Post post);
}
