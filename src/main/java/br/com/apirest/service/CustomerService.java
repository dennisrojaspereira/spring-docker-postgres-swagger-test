package br.com.apirest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirest.model.Customer;
import br.com.apirest.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

	public Customer getById(long id) {
		Optional<Customer> data = customerRepository.findById(id);
		if ( data.isPresent()) {
			return data.get();
		}
		return null;

    	
	}
	
	
	
	
	

}
