package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.LikesDto;
import com.blog.services.LikesService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth/")
public class LikesController {


	@Autowired
	private LikesService likesService;
	
	@Operation(summary = "Create Like", 
			description = "This endpoint allows you to like a post by a specific user.")
	@PostMapping("posts/{postId}/users/{userId}/likes")
	public ResponseEntity<LikesDto> createLike(@PathVariable Integer postId,@PathVariable Integer userId) {
		LikesDto likesDto = new LikesDto();
		LikesDto CreateLike = this.likesService.createLike(likesDto ,postId, userId);
		return new ResponseEntity<LikesDto>(CreateLike, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Delete Like",
			description = "This endpoint allows you to unlike a post by a specific user.")
    @DeleteMapping("posts/{postId}/users/{userId}/likes")
    public ResponseEntity<ApiResponse> deleteLike(
            @PathVariable Integer postId,
            @PathVariable Integer userId) {
        
        List<LikesDto> likeToDelete = this.likesService.getLikeByPostAndUser(postId, userId);
        this.likesService.deleteLike(likeToDelete.get(0).getId());
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post unliked successfully !!", true), HttpStatus.OK);
    }
	
    
	@Operation(summary = "Get Likes Count by Post",
			description = "This endpoint allows you to retrieve the count of likes for a specific post.")
    @GetMapping("posts/{postId}/likes/count")
    public ResponseEntity<LikesDto> getLikesCountByPost(@PathVariable Integer postId) {
        LikesDto likeCountDto = this.likesService.getLikesCountByPost(postId);
        return new ResponseEntity<>(likeCountDto, HttpStatus.OK);
    }
    
	@Operation(summary = "Get Likes by Post",
			description = "This endpoint allows you to retrieve all likes for a specific post.")
    @GetMapping("posts/{postId}/likes")
    public ResponseEntity<List<LikesDto>> getLikedByPost(@PathVariable Integer postId) {
        List<LikesDto> likes = this.likesService.getLikedByPost(postId);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }
}
