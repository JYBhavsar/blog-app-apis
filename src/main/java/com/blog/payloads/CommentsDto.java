package com.blog.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentsDto {

	private Long id;
	private String content;
	private Date createdAt;
	private Date updatedAt;

	private PostDto post;

	private UserDto user;
}
