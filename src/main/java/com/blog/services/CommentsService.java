package com.blog.services;

import java.util.List;

import com.blog.payloads.CommentsDto;

public interface CommentsService {

	CommentsDto createComment(CommentsDto commentsDto, Integer postId, Integer userId);
    CommentsDto updateComment(CommentsDto commentsDto, Long commentId);
    void deleteComment(Long commentId);
    
    List<CommentsDto> getCommentsByPost(Integer postId);
    CommentsDto getCommentById(Long commentId);
	
}
