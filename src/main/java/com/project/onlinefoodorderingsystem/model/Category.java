package com.project.onlinefoodorderingsystem.model;

import javax.persistence.CascadeType;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Table(name = "category")
@Entity(name = "category")

public class Category {

	private long category_id;
	private String category_title;
	private String category_description;
	private MultipartFile category_image;
	private String category_image_filename;
	
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_title=" + category_title + ", category_description="
				+ category_description + ", category_image=" + category_image + ", category_image_filename="
				+ category_image_filename + "]";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getCategory_id() {
		return category_id;
	}
	
	public Category() {
		
	}
	public Category(long category_id, String category_title, String category_description, MultipartFile category_image,
			String category_image_filename) {
		super();
		this.category_id = category_id;
		this.category_title = category_title;
		this.category_description = category_description;
		this.category_image = category_image;
		this.category_image_filename = category_image_filename;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}
	public String getCategory_title() {
		return category_title;
	}
	public void setCategory_title(String category_title) {
		this.category_title = category_title;
	}
	public String getCategory_description() {
		return category_description;
	}
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}
	
	public void setCategory_image(MultipartFile category_image) {
		this.category_image = category_image;
	}
	public String getCategory_image_filename() {
		return category_image_filename;
	}
	public void setCategory_image_filename(String category_image_filename) {
		this.category_image_filename = category_image_filename;
	}
	
	
}
