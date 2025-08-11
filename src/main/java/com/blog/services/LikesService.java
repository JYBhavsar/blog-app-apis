package com.blog.services;

import java.util.List;

import com.blog.payloads.LikesDto;

public interface LikesService {
	
	LikesDto createLike(LikesDto likesDto, Integer postId, Integer userId);
	void deleteLike(Long likeId);
	
	
	List<LikesDto> getLikedByPost(Integer postId);
	
	List<LikesDto> getLikeByPostAndUser(Integer postId, Integer userId);
	
	LikesDto getLikesCountByPost(Integer postId);
}
