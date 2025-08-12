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
import com.blog.payloads.TagDto;
import com.blog.services.TagService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth/tags")
public class TagController {

    @Autowired
    private TagService tagService;
    
    
    @Operation(summary = "Create Tag", 
			description = "This endpoint allows you to create a new tag.")
    @PostMapping("/")
    public ResponseEntity<TagDto> createTag(@RequestBody TagDto tagDto) {
        TagDto createdTag = this.tagService.createTag(tagDto);
        return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
    }
    
    
    @Operation(summary = "Update Tag",
    		description = "This endpoint allows you to update an existing tag by its ID.")
    @PutMapping("/{tagId}")
    public ResponseEntity<TagDto> updateTag(@PathVariable Integer tagId, @RequestBody TagDto tagDto) {
        TagDto updatedTag = this.tagService.updateTag(tagId, tagDto);
        return new ResponseEntity<>(updatedTag, HttpStatus.OK);
    }
    
    @Operation(summary = "Delete Tag", 
			description = "This endpoint allows you to delete a tag by its ID.")
    @DeleteMapping("/{tagId}")
    public ResponseEntity<ApiResponse> deleteTag(@PathVariable Integer tagId) {
        this.tagService.deleteTag(tagId);
        return new ResponseEntity<>(new ApiResponse("Tag deleted successfully!", true), HttpStatus.OK);
    }
    
    @Operation(summary = "Get Tag by ID", 
			description = "This endpoint allows you to retrieve a tag by its ID.")
    @GetMapping("/{tagId}")
    public ResponseEntity<TagDto> getTagById(@PathVariable Integer tagId) {
        TagDto tag = this.tagService.getTagById(tagId);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
    
    @Operation(summary = "Get All Tags",
    		description = "This endpoint allows you to retrieve all tags.")
    @GetMapping("/")
    public ResponseEntity<List<TagDto>> getAllTags() {
        List<TagDto> tags = this.tagService.getAllTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
}
