package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Tags;

public interface TagsRepo extends JpaRepository<Tags, Integer> {

	// This interface extends JpaRepository, which provides basic CRUD operations
	// for the Tags entity. The first parameter is the entity type, and the second
	// parameter is the type of the entity's ID.
	// Additional query methods can be defined here if needed

}
