package com.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Tag;

public interface TagRepo extends JpaRepository<Tag, Integer> {

	Optional<Tag> findByTagName(String name);

}
