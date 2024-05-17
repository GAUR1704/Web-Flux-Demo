package com.prowings.webfluxdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.webfluxdemo.dto.Customer;
import com.prowings.webfluxdemo.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		
		return customerService.loadAllCustomers();
	}
	
	@GetMapping(value = "/customersStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Customer> getAllCustomersStream(){
		
		return customerService.loadAllCustomersStream();
	}

}
