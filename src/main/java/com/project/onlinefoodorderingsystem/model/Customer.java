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

@Table(name = "customer")
@Entity(name = "customer")

public class Customer {

	private long customer_id;
	private String customer_email;
	private String customer_password;
	private String customer_first_name;
	private String customer_last_name;
	private String customer_dob;

	private String customer_address;
	private String customer_city;
	private String customer_state;
	private String customer_country;
	private String customer_mobile;
	private String customer_nationalty;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getCustomer_id() {
		return customer_id;
	}

	public Customer(long customer_id, String customer_email, String customer_password, String customer_first_name,
			String customer_last_name, String customer_dob, String customer_address, String customer_city,
			String customer_state, String customer_country, String customer_mobile, String customer_nationalty) {
		super();
		this.customer_id = customer_id;
		this.customer_email = customer_email;
		this.customer_password = customer_password;
		this.customer_first_name = customer_first_name;
		this.customer_last_name = customer_last_name;
		this.customer_dob = customer_dob;
		this.customer_address = customer_address;
		this.customer_city = customer_city;
		this.customer_state = customer_state;
		this.customer_country = customer_country;
		this.customer_mobile = customer_mobile;
		this.customer_nationalty = customer_nationalty;
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", customer_email=" + customer_email + ", customer_password="
				+ customer_password + ", customer_first_name=" + customer_first_name + ", customer_last_name="
				+ customer_last_name + ", customer_dob=" + customer_dob + ", customer_address=" + customer_address
				+ ", customer_city=" + customer_city + ", customer_state=" + customer_state + ", customer_country="
				+ customer_country + ", customer_mobile=" + customer_mobile + ", customer_nationalty="
				+ customer_nationalty + "]";
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_password() {
		return customer_password;
	}

	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}

	public String getCustomer_first_name() {
		return customer_first_name;
	}

	public void setCustomer_first_name(String customer_first_name) {
		this.customer_first_name = customer_first_name;
	}

	public String getCustomer_last_name() {
		return customer_last_name;
	}

	public void setCustomer_last_name(String customer_last_name) {
		this.customer_last_name = customer_last_name;
	}

	public String getCustomer_dob() {
		return customer_dob;
	}

	public void setCustomer_dob(String customer_dob) {
		this.customer_dob = customer_dob;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCustomer_city() {
		return customer_city;
	}

	public void setCustomer_city(String customer_city) {
		this.customer_city = customer_city;
	}

	public String getCustomer_state() {
		return customer_state;
	}

	public void setCustomer_state(String customer_state) {
		this.customer_state = customer_state;
	}

	public String getCustomer_country() {
		return customer_country;
	}

	public void setCustomer_country(String customer_country) {
		this.customer_country = customer_country;
	}

	public String getCustomer_mobile() {
		return customer_mobile;
	}

	public void setCustomer_mobile(String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}

	public String getCustomer_nationalty() {
		return customer_nationalty;
	}

	public void setCustomer_nationalty(String customer_nationalty) {
		this.customer_nationalty = customer_nationalty;
	}
	
	public Customer() {
		
	}
	
	

}
