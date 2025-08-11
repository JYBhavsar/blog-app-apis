package com.blog.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "post_title", nullable = false)
	private String title;
	
	@Column(name = "post_slug", nullable = false)
	private String slug;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "status", nullable = false)
	private String status; // draft, published
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date createdAt;
	
	@Column(name = "updated_at")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "tag_id")
	private Tags tags;
	
	@ManyToOne
	private User user;
	
}
