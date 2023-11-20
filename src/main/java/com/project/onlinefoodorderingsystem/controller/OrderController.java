package com.project.onlinefoodorderingsystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.onlinefoodorderingsystem.exception.ResourceNotFoundException;
import com.project.onlinefoodorderingsystem.model.Customer;
import com.project.onlinefoodorderingsystem.model.Order;
import com.project.onlinefoodorderingsystem.model.Product;
import com.project.onlinefoodorderingsystem.model.Sell;
import com.project.onlinefoodorderingsystem.repository.OrderRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@GetMapping("/orders/search/{name}")
	public List<Order> getOrderByName(@PathVariable(value = "name") String orderName) {
			return orderRepository.serchUserByName(orderName);
	}
	
	@GetMapping("/orders/search-state/{state}")
	public List<Order> serchUserByState(@PathVariable(value = "state") String orderState) {
			return orderRepository.serchUserByState(orderState);
	}
	
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long orderId)
			throws ResourceNotFoundException {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
		return ResponseEntity.ok().body(order);
	}

	@PostMapping("/orders")
	public Order createOrder(@Valid @RequestBody Order order) {
		return orderRepository.save(order);
	}

	@PutMapping("/orders/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") Long orderId,
			@Valid @RequestBody Order orderDetails) throws ResourceNotFoundException {
		final Order updatedOrder = orderRepository.save(orderDetails);
		return ResponseEntity.ok(updatedOrder);
	}
	
	@GetMapping("/orders/all-orders")
	public ArrayList getAllEmployeeFields() {
		 Query q = entityManager.createQuery("SELECT cust, ord from orders ord, customer cust WHERE customer_id = order_customer_id");
		 List<Object[]> sell = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : sell ) {
			  Order order_details = (Order)row[ 1 ];
			  Customer customer_details = (Customer)row[ 0 ];
			  
			  HashMap<String, String> results = new HashMap();
			    results.put("order_id",String.valueOf(order_details.getOrder_id()));
			    results.put("order_customer_id",order_details.getOrder_customer_id());
			    results.put("order_total",order_details.getOrder_total());
			    results.put("order_status",order_details.getOrder_status());
			    results.put("order_date",order_details.getOrder_date());
			    results.put("customer_name",customer_details.getCustomer_first_name()+" "+customer_details.getCustomer_last_name());
			    results.put("customer_mobile",String.valueOf(customer_details.getCustomer_mobile()));
				
				resultArray.add(results);
			 
		 }	 

        return resultArray;
	}
	@GetMapping("/orders/customer-orders/{id}")
	public ArrayList getAllCustomerOrders(@PathVariable(value = "id") Long orderId) {
		 Query q = entityManager.createQuery("SELECT cust, ord from orders ord, customer cust WHERE order_status = 'Paid' AND customer_id = order_customer_id AND order_customer_id = ?1");
		 List<Object[]> sell = q.setParameter(1, orderId).getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : sell ) {
			  Order order_details = (Order)row[ 1 ];
			  Customer customer_details = (Customer)row[ 0 ];
			  
			  HashMap<String, String> results = new HashMap();
			    results.put("order_id",String.valueOf(order_details.getOrder_id()));
			    results.put("order_customer_id",order_details.getOrder_customer_id());
			    results.put("order_total",order_details.getOrder_total());
			    results.put("order_status",order_details.getOrder_status());
			    results.put("order_date",order_details.getOrder_date());
			    results.put("customer_name",customer_details.getCustomer_first_name()+" "+customer_details.getCustomer_last_name());
			    results.put("customer_mobile",String.valueOf(customer_details.getCustomer_mobile()));
				
				resultArray.add(results);
			 
		 }	 

        return resultArray;
	}
	
	@GetMapping("/orders/details/{id}")
	public ArrayList getOrderDetails(@PathVariable(value = "id") Long orderId) {
		 Query q = entityManager.createQuery("SELECT cust, ord from orders ord, customer cust WHERE customer_id = order_customer_id AND order_id = ?1");
		 List<Object[]> sell = q.setParameter(1, orderId).getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : sell ) {
			  Order order_details = (Order)row[ 1 ];
			  Customer customer_details = (Customer)row[ 0 ];
			  
			  HashMap<String, String> results = new HashMap();
			    results.put("order_id",String.valueOf(order_details.getOrder_id()));
			    results.put("order_customer_id",order_details.getOrder_customer_id());
			    results.put("order_total",order_details.getOrder_total());
			    results.put("order_status",order_details.getOrder_status());
			    results.put("order_date",order_details.getOrder_date());
			    results.put("customer_name",customer_details.getCustomer_first_name()+" "+customer_details.getCustomer_last_name());
			    results.put("customer_mobile",String.valueOf(customer_details.getCustomer_mobile()));
				
				resultArray.add(results);
			 
		 }	 

        return resultArray;
	}

	@DeleteMapping("/orders/{id}")
	public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Long orderId)
			throws ResourceNotFoundException {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

		orderRepository.delete(order);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
