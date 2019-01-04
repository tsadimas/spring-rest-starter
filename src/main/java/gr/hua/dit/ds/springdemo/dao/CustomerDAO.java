package gr.hua.dit.ds.springdemo.dao;

import java.util.List;

import gr.hua.dit.ds.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer customer);
	
	public Customer getCustomer(int id);

	public void deleteCustomer(int id);
}
