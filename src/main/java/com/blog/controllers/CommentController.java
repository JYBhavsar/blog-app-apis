package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentsDto;
import com.blog.services.CommentsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth/")
public class CommentController {

    @Autowired
    private CommentsService commentsService;
    
    @Operation(summary = "Create Comment", 
			description = "This endpoint allows you to create a new comment on a post.")
    @PostMapping("posts/{postId}/users/{userId}/comments")
    public ResponseEntity<CommentsDto> createComment(
        @RequestBody CommentsDto commentsDto,
        @PathVariable Integer postId,
        @PathVariable Integer userId) {
        
        CommentsDto createdComment = this.commentsService.createComment(commentsDto, postId, userId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Comment by ID",
    		description = "This endpoint allows you to retrieve a comment by its ID.")
    @PutMapping("comments/{commentId}")
    public ResponseEntity<CommentsDto> updateComment(
        @RequestBody CommentsDto commentsDto,
        @PathVariable Long commentId) {
        
        CommentsDto updatedComment = this.commentsService.updateComment(commentsDto, commentId);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }
    
    @Operation(summary = "Delete Comment by ID",
			description = "This endpoint allows you to delete a comment by its ID.")
    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {
        this.commentsService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully!", true), HttpStatus.OK);
    }
 
    @Operation(summary = "Get Comments by Post",
    		description = "This endpoint allows you to retrieve all comments for a specific post.")
    @GetMapping("posts/{postId}/comments")
    public ResponseEntity<List<CommentsDto>> getCommentsByPost(@PathVariable Integer postId) {
        List<CommentsDto> comments = this.commentsService.getCommentsByPost(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
