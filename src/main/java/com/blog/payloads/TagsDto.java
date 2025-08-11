package com.blog.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TagsDto {

	private Long id;
	private String tagName;
	private Date createdAt;
	private Date updatedAt;
}
