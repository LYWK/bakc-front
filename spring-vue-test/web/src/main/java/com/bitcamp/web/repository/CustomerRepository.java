package com.bitcamp.web.repository;


import com.bitcamp.web.domain.CustomerDTO;
import com.bitcamp.web.entities.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CustomerRepository
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long > {
        public Customer  findByCustomerIdAndPassword(String customerId,String password);
   // public CustomerDTO findOne2(Customer customer);
   // public Customer    customerId(Long id);
}