package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Tag;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.TagDto;
import com.blog.repositories.TagRepo;
import com.blog.services.TagService;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public TagDto createTag(TagDto tagDto) {
			Tag tag = this.modelMapper.map(tagDto, Tag.class);
			tag.setCreatedAt(new Date());
	        Tag savedTag = this.tagRepo.save(tag);
	        return this.modelMapper.map(savedTag, TagDto.class);
	}

	@Override
	public TagDto updateTag(Integer tagId, TagDto tagDto) {
		Tag tag = this.tagRepo.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Tag Id", tagId));
		tag.setTagName(tagDto.getTagName());
		tag.setUpdatedAt(new Date());
		
		Tag updatedTag = this.tagRepo.save(tag);
		return this.modelMapper.map(updatedTag, TagDto.class);
	}

	@Override
	public void deleteTag(Integer tagId) {
		Tag tag = this.tagRepo.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Tag Id", tagId));
		this.tagRepo.delete(tag);
	}

	@Override
	public TagDto getTagById(Integer tagId) {
		Tag tag = this.tagRepo.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Tag Id", tagId));
		return this.modelMapper.map(tag, TagDto.class);
	}

	@Override
	public List<TagDto> getAllTags() {
		List<Tag> tags = this.tagRepo.findAll();
		return tags.stream()
                .map(tag -> this.modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
	}
}
