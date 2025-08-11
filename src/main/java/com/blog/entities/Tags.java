package com.blog.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tags")
@NoArgsConstructor
@Getter
@Setter
public class Tags {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tag_name", nullable = false)
	private String tagName;
	
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date createdAt;
	
	@Column(name = "updated_at")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date updatedAt;
}
