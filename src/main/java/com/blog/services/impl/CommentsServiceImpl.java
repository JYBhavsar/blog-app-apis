package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comments;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentsDto;
import com.blog.repositories.CommentsRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsRepo commentsRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentsDto createComment(CommentsDto commentsDto, Integer postId, Integer userId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		Comments comment = this.modelMapper.map(commentsDto, Comments.class);

		comment.setPost(post);
		comment.setUser(user);
		comment.setCreatedAt(new Date());
		comment.setUpdatedAt(new Date());

		Comments savedComment = this.commentsRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentsDto.class);
	}

	@Override
	public CommentsDto updateComment(CommentsDto commentsDto, Long commentId) {
		Comments comment = this.commentsRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));

		// Only allow updating content
		comment.setContent(commentsDto.getContent());
		comment.setUpdatedAt(new Date());

		Comments updatedComment = this.commentsRepo.save(comment);
		return this.modelMapper.map(updatedComment, CommentsDto.class);
	}

	@Override
	public void deleteComment(Long commentId) {
		Comments comment = this.commentsRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		this.commentsRepo.delete(comment);
	}

	@Override
	public CommentsDto getCommentById(Long commentId) {
		Comments comment = this.commentsRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));
		return this.modelMapper.map(comment, CommentsDto.class);
	}

	@Override
	public List<CommentsDto> getCommentsByPost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		List<Comments> comments = this.commentsRepo.findByPostOrderByCreatedAtAsc(post);

		return comments.stream().map(comment -> this.modelMapper.map(comment, CommentsDto.class))
				.collect(Collectors.toList());
	}
}
