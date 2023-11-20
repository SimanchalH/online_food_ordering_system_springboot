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

@Table(name = "sell")
@Entity(name = "sell")

public class Sell {

	private long sell_id;
	private String sell_order_id;
	private String sell_product_id;
	private String sell_units;
	private String sell_price_per_unit;
	private String sell_total_cost;
	
	public Sell() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getSell_id() {
		return sell_id;
	}

	public String getSell_order_id() {
		return sell_order_id;
	}

	public void setSell_order_id(String sell_order_id) {
		this.sell_order_id = sell_order_id;
	}

	public String getSell_product_id() {
		return sell_product_id;
	}

	public void setSell_product_id(String sell_product_id) {
		this.sell_product_id = sell_product_id;
	}

	public String getSell_units() {
		return sell_units;
	}

	public void setSell_units(String sell_units) {
		this.sell_units = sell_units;
	}

	public String getSell_price_per_unit() {
		return sell_price_per_unit;
	}

	public void setSell_price_per_unit(String sell_price_per_unit) {
		this.sell_price_per_unit = sell_price_per_unit;
	}

	public String getSell_total_cost() {
		return sell_total_cost;
	}

	public void setSell_total_cost(String sell_total_cost) {
		this.sell_total_cost = sell_total_cost;
	}

	public void setSell_id(long sell_id) {
		this.sell_id = sell_id;
	}

	@Override
	public String toString() {
		return "Sell [sell_id=" + sell_id + ", sell_order_id=" + sell_order_id + ", sell_product_id=" + sell_product_id
				+ ", sell_units=" + sell_units + ", sell_price_per_unit=" + sell_price_per_unit + ", sell_total_cost="
				+ sell_total_cost + "]";
	}

	public Sell(long sell_id, String sell_order_id, String sell_product_id, String sell_units,
			String sell_price_per_unit, String sell_total_cost) {
		super();
		this.sell_id = sell_id;
		this.sell_order_id = sell_order_id;
		this.sell_product_id = sell_product_id;
		this.sell_units = sell_units;
		this.sell_price_per_unit = sell_price_per_unit;
		this.sell_total_cost = sell_total_cost;
	}
	
	
}
