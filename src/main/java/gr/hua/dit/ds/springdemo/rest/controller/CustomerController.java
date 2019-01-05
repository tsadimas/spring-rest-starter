package gr.hua.dit.ds.springdemo.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.ds.springdemo.entity.Customer;
import gr.hua.dit.ds.springdemo.rest.CustomerNotFoundException;
import gr.hua.dit.ds.springdemo.dao.CustomerDAO;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	// inject the customer dao
		@Autowired
		private CustomerDAO customerDAO;


	@GetMapping("/customers")
	public List<Customer> listCustomers() {
		// get customers from dao
		List<Customer> customers = customerDAO.getCustomers();
		return customers;
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = customerDAO.getCustomer(customerId);
		
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found " + customerId);
		}
		
		return theCustomer;
	} 
	
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update
		// sample data (raw-application/json)
//		{
//			"firstName": "Alekos",
//			"lastName": "Sakellarios",
//			"email": "alekos@hua.gr"
//		}
		
		theCustomer.setId(0);
		
		customerDAO.saveCustomer(theCustomer);
		
		return theCustomer;
	}

	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		// sample data (raw-application/json)
//		{
//			"id": "78",
//			"firstName": "Alekos",
//			"lastName": "Sakellarios",
//			"email": "alekos@hua.gr"
//		}
		
		customerDAO.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = customerDAO.getCustomer(customerId);
		
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found " + customerId);
		}
		customerDAO.deleteCustomer(customerId);
		return "Deleted customer id - " + customerId;
	}
	
}
