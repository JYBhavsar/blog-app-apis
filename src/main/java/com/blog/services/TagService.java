package com.blog.services;

import java.util.List;

import com.blog.payloads.TagDto;

public interface TagService {
	
		TagDto createTag(TagDto tagDto);
	    TagDto updateTag(Integer tagId, TagDto tagDto);
	    void deleteTag(Integer tagId);
	    TagDto getTagById(Integer tagId);
	    List<TagDto> getAllTags();
}
