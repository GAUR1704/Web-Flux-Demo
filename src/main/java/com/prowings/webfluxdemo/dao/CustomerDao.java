package com.prowings.webfluxdemo.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.prowings.webfluxdemo.dto.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {
	
	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<Customer> getCustomer() {

		return IntStream.rangeClosed(1, 10)
				.peek(CustomerDao ::sleepExecution)
				.peek(i -> System.out.println("Processing count : " + i))
				.mapToObj(i -> new Customer(i, "Customer" + i))
				.collect(Collectors.toList());
	}

	public Flux<Customer> getCustomerStream() {
		return Flux.range(1, 50)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("Processing count in stream flow: " + i))
				.map(i -> new Customer(i, "Customer" + i));
	}

}
