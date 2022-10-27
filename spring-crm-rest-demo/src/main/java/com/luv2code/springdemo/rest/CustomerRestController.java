package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);

        if(customer == null) {
            throw new CustomerNotFoundException("Customer with id: " + customerId + " not found!");
        }

        return customer;
    }

    @PostMapping("/customer")
    public Customer addCustomer(@RequestBody Customer customer) {

        customer.setId(0);
        customerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/customer")
    public Customer updateCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/customer/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {

        Customer customer = customerService.getCustomer(customerId);

        if(customer == null) {
            throw new CustomerNotFoundException("Customer with id: " + customerId + " not found");
        }

        customerService.deleteCustomer(customerId);
        return "Customer with id: " + customerId + " deleted";
    }
}
