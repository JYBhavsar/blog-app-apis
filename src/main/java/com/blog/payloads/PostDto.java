package com.blog.payloads;

import java.util.Date;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	private Integer id;
	private String title;
	private String slug;
	private String content;
	private String status;
	private String imageUrl;

	private Date createdAt;
	private Date updatedAt;
	
	private CategoryDto category;
	private UserDto user;
	
}
