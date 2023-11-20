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

@Table(name = "payment")
@Entity(name = "payment")

public class Payment {

	private long payment_id;
	private String payment_customer_id;
	private String payment_date;
	private String payment_amount;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getPayment_id() {
		return payment_id;
	}
	
	public Payment() {
		
	}

	public String getPayment_customer_id() {
		return payment_customer_id;
	}

	public void setPayment_customer_id(String payment_customer_id) {
		this.payment_customer_id = payment_customer_id;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public String getPayment_amount() {
		return payment_amount;
	}

	public void setPayment_amount(String payment_amount) {
		this.payment_amount = payment_amount;
	}

	public void setPayment_id(long payment_id) {
		this.payment_id = payment_id;
	}

	@Override
	public String toString() {
		return "Payment [payment_id=" + payment_id + ", payment_customer_id=" + payment_customer_id + ", payment_date="
				+ payment_date + ", payment_amount=" + payment_amount + "]";
	}

	public Payment(long payment_id, String payment_customer_id, String payment_date, String payment_amount) {
		super();
		this.payment_id = payment_id;
		this.payment_customer_id = payment_customer_id;
		this.payment_date = payment_date;
		this.payment_amount = payment_amount;
	}
		

}
