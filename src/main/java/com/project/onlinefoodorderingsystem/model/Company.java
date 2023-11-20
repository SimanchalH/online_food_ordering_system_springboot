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

@Table(name = "company")
@Entity(name = "company")

public class Company {

	private long company_id;
	private String company_title;
	private String company_description;
	private MultipartFile company_image;
	private String company_image_filename;
	
	@Override
	public String toString() {
		return "Company [company_id=" + company_id + ", company_title=" + company_title + ", company_description="
				+ company_description + ", company_image=" + company_image + ", company_image_filename="
				+ company_image_filename + "]";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getCompany_id() {
		return company_id;
	}
	
	public Company() {
		
	}
	public Company(long company_id, String company_title, String company_description, MultipartFile company_image,
			String company_image_filename) {
		super();
		this.company_id = company_id;
		this.company_title = company_title;
		this.company_description = company_description;
		this.company_image = company_image;
		this.company_image_filename = company_image_filename;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
	public String getCompany_title() {
		return company_title;
	}
	public void setCompany_title(String company_title) {
		this.company_title = company_title;
	}
	public String getCompany_description() {
		return company_description;
	}
	public void setCompany_description(String company_description) {
		this.company_description = company_description;
	}
	
	public void setCompany_image(MultipartFile company_image) {
		this.company_image = company_image;
	}
	public String getCompany_image_filename() {
		return company_image_filename;
	}
	public void setCompany_image_filename(String company_image_filename) {
		this.company_image_filename = company_image_filename;
	}
	
	
}
