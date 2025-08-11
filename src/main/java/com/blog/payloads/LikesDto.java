package com.blog.payloads;

import java.util.Date;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LikesDto {

	private Long id;
	private Date createdAt;
	private Date updatedAt;
	
	private PostDto postDto;
	
	private UserDto userDto;
	
	private Long likeCount;
	
	public LikesDto(Long likeCount) {
        this.likeCount = likeCount;
    }
	
}
