package com.blog.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Likes;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.LikesDto;
import com.blog.repositories.LikesRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.LikesService;

@Service
public class LikesServiceImpl implements LikesService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LikesRepo likesRepository;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostRepo postRepo;

	@Override
	public LikesDto createLike(LikesDto likesDto, Integer postId, Integer userId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		// Check if the user has already liked the post
		this.likesRepository.findByPostAndUser(post, user).ifPresent(existingLike -> {
			throw new IllegalArgumentException("User has already liked this post.");
		});

		Likes likes = this.modelMapper.map(likesDto, Likes.class);
		likes.setPost(post);
		likes.setUser(user);
		likes.setCreatedAt(new Date());

		Likes addedLikes = this.likesRepository.save(likes);
		return this.modelMapper.map(addedLikes, LikesDto.class);
	}

	@Override
	public void deleteLike(Long likeId) {
		Likes likes = this.likesRepository.findById(likeId)
				.orElseThrow(() -> new ResourceNotFoundException("Like", "Like Id", likeId));
		// Check if the like exists before attempting to delete
		if (likes == null) {
			throw new ResourceNotFoundException("Like", "Like Id", likeId);
		}
		this.likesRepository.delete(likes);
	}

	@Override
	public List<LikesDto> getLikedByPost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		List<Likes> likesList = this.likesRepository.findByPost(post);
		if (likesList != null && !likesList.isEmpty()) {
			return likesList.stream().map(this::LikesToDto).toList();
		}

		return null;
	}

	@Override
	public List<LikesDto> getLikeByPostAndUser(Integer postId, Integer userId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		Likes likes = this.likesRepository.findByPostAndUser(post, user)
				.orElseThrow(() -> new ResourceNotFoundException("Like", "Post Id and User Id", userId));
		if (likes != null) {
			return List.of(this.LikesToDto(likes));
		}
		return null;
	}

	@Override
	public LikesDto getLikesCountByPost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		Long likesCount = this.likesRepository.countByPost(post);

		return new LikesDto(likesCount);
	}

	public Likes dtoToLikes(LikesDto likesDto) {
		Likes likes = this.modelMapper.map(likesDto, Likes.class);
		return likes;
	}

	public LikesDto LikesToDto(Likes likes) {
		LikesDto likesDto = this.modelMapper.map(likes, LikesDto.class);
		return likesDto;
	}

}
