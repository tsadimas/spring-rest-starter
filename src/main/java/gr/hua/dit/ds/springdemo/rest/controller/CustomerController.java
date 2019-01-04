package gr.hua.dit.ds.springdemo.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.ds.springdemo.entity.Customer;
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

}
