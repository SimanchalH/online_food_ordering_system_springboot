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

@Table(name = "product")
@Entity(name = "product")

public class Product {

	private long product_id;
	private String product_category_id;
	private String product_title;
	private String product_description;
	private MultipartFile product_image;
	private String product_image_filename;
	private String product_cost;
	
	public Product(String product_cost) {
		super();
		this.product_cost = product_cost;
	}
	public String getProduct_cost() {
		return product_cost;
	}
	public void setProduct_cost(String product_cost) {
		this.product_cost = product_cost;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public String getProduct_category_id() {
		return product_category_id;
	}
	public void setProduct_category_id(String product_category_id) {
		this.product_category_id = product_category_id;
	}
	public String getProduct_title() {
		return product_title;
	}
	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public void setProduct_image(MultipartFile product_image) {
		this.product_image = product_image;
	}
	public String getProduct_image_filename() {
		return product_image_filename;
	}
	public void setProduct_image_filename(String product_image_filename) {
		this.product_image_filename = product_image_filename;
	}
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_category_id=" + product_category_id + ", product_title=" + product_title
				+ ", product_description=" + product_description + ", product_image=" + product_image + ", product_image_filename="
				+ product_image_filename + ", product_cost=" + product_cost + "]";
	}
	public Product() {
		
	}
	public Product(long product_id, String product_category_id, String product_title, String product_description,
			MultipartFile product_image, String product_image_filename) {
		super();
		this.product_id = product_id;
		this.product_category_id = product_category_id;
		this.product_title = product_title;
		this.product_description = product_description;
		this.product_image = product_image;
		this.product_image_filename = product_image_filename;
	}
}
