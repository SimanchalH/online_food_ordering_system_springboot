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
import com.project.onlinefoodorderingsystem.model.Country;
import com.project.onlinefoodorderingsystem.model.Customer;
import com.project.onlinefoodorderingsystem.model.Department;
import com.project.onlinefoodorderingsystem.model.Employee;
import com.project.onlinefoodorderingsystem.model.Payment;
import com.project.onlinefoodorderingsystem.repository.PaymentRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/payments")
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@GetMapping("/payments/search/{name}")
	public List<Payment> getPaymentByName(@PathVariable(value = "name") String paymentName) {
			return paymentRepository.serchUserByName(paymentName);
	}

	
	@GetMapping("/payments/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable(value = "id") Long paymentId)
			throws ResourceNotFoundException {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));
		return ResponseEntity.ok().body(payment);
	}

	@PostMapping("/payments")
	public Payment createPayment(@Valid @RequestBody Payment payment) {
		return paymentRepository.save(payment);
	}

	@PutMapping("/payments/{id}")
	public ResponseEntity<Payment> updatePayment(@PathVariable(value = "id") Long paymentId,
			@Valid @RequestBody Payment paymentDetails) throws ResourceNotFoundException {
		final Payment updatedPayment = paymentRepository.save(paymentDetails);
		return ResponseEntity.ok(updatedPayment);
	}
	
	@GetMapping("/payments/all-payment/{id}")
	public ArrayList getAllPayment(@PathVariable(value = "id") String customer_id) {
		
		String SQL = "SELECT payment, cust from payment payment, customer cust WHERE customer_id = payment_customer_id";
		 Query q = entityManager.createQuery(SQL);
		 if(!customer_id.equals("0")) {
			 System.out.print("Customer Id : "+customer_id);

			 SQL = "SELECT payment, cust from payment payment, customer cust WHERE customer_id = payment_customer_id AND customer_id = :customer_id";
			 q = entityManager.createQuery(SQL);
			 q.setParameter("customer_id", customer_id);
		 } 
		 List<Object[]> payment = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : payment ) {
			  Payment payment_details = (Payment)row[ 0 ];
			  Customer customer_details = (Customer)row[ 1 ];
			
			    HashMap<String, String> results = new HashMap();
			   
				results.put("customer_name",customer_details.getCustomer_first_name()+" "+customer_details.getCustomer_last_name());
				results.put("payment_id",String.valueOf(payment_details.getPayment_id()));
				results.put("payment_amount",payment_details.getPayment_amount());
				results.put("payment_customer_id",String.valueOf(payment_details.getPayment_customer_id()));
				results.put("payment_date",payment_details.getPayment_date());
				
			    resultArray.add(results);
			 
		 }	 

        return resultArray;
	}

	@DeleteMapping("/payments/{id}")
	public Map<String, Boolean> deletePayment(@PathVariable(value = "id") Long paymentId)
			throws ResourceNotFoundException {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));
		
		paymentRepository.delete(payment);
		
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
