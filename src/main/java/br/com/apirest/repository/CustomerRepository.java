package br.com.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apirest.model.Customer;

public interface CustomerRepository  extends JpaRepository<Customer,Long> {


}