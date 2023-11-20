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

@Table(name = "comments")
@Entity(name = "comments")

public class Comments {

	private long comments_id;
	private String comments_user_id;
	private String comments_product_id;
	
	public Comments(String comments_product_id) {
		super();
		this.comments_product_id = comments_product_id;
	}
	public String getComments_product_id() {
		return comments_product_id;
	}
	public void setComments_product_id(String comments_product_id) {
		this.comments_product_id = comments_product_id;
	}

	private String comments_title;
	private String comments_description;
	private String comments_date;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getComments_id() {
		return comments_id;
	}
	public void setComments_id(long comments_id) {
		this.comments_id = comments_id;
	}
	public String getComments_user_id() {
		return comments_user_id;
	}
	public void setComments_user_id(String comments_user_id) {
		this.comments_user_id = comments_user_id;
	}
	public String getComments_title() {
		return comments_title;
	}
	public void setComments_title(String comments_title) {
		this.comments_title = comments_title;
	}
	public String getComments_description() {
		return comments_description;
	}
	public void setComments_description(String comments_description) {
		this.comments_description = comments_description;
	}
	public String getComments_date() {
		return comments_date;
	}
	public void setComments_date(String comments_date) {
		this.comments_date = comments_date;
	}
	@Override
	public String toString() {
		return "Comments [comments_id=" + comments_id + ", comments_user_id=" + comments_user_id + ", comments_product_id="
				+ comments_product_id + ", comments_title=" + comments_title + ", comments_description="
				+ comments_description + ", comments_date=" + comments_date + "]";
	}
	public Comments(long comments_id, String comments_user_id, String comments_title, String comments_description,
			String comments_date) {
		super();
		this.comments_id = comments_id;
		this.comments_user_id = comments_user_id;
		this.comments_title = comments_title;
		this.comments_description = comments_description;
		this.comments_date = comments_date;
	}
	
	public Comments() {
		
	}
	
}
