package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatePost = this.postRepo.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", id));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		
		
		List<Post> allPost = pagePost.getContent();
		List<PostDto> postDtos =  allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse(postDtos, pagePost.getNumber(), 
				pagePost.getSize(), 
				pagePost.getTotalElements(), 
				pagePost.getTotalPages(), 
				pagePost.isLast());
	
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer id) {
		Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer id) {
		
		Category cat = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		List<PostDto> postDtos = posts.stream()
			    .map((post) -> this.modelMapper.map(post, PostDto.class))
			    .collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));

		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> postDtos = posts.stream()
			    .map((post) -> this.modelMapper.map(post, PostDto.class))
			    .collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
