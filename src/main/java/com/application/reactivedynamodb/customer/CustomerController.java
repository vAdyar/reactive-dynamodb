package com.application.reactivedynamodb.customer;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController( CustomerService customerService ) {
		this.customerService = customerService;
	}
	
	@PostMapping("")//C
	public Mono<Result> saveCustomer( @RequestBody Customer customer ) {
		return customerService.createNewCustomer(customer);
	}
	
	@GetMapping("/{customerId}")//R
	public Mono<Customer> getCustomer( @PathVariable("customerId") String customerId ) {
		return customerService.getCustomerByCustomerId(customerId);
	}
	
	@PutMapping("")//U
	public Mono<Result> updateOrCreateCustomer( @RequestBody Customer customer ) {
		return customerService.updateExistingOrCreateCustomer(customer);
	}
	
	@DeleteMapping("/{customerId}")//D
	public Mono<Result> deleteCustomer( @PathVariable("customerId") String customerId ) {
		return customerService.deleteCustomerByCustomerId(customerId);
	}
	
	@PutMapping("/{customerId}")
	public Mono<Result> updateCustomer( @RequestBody Customer customer, @PathVariable("customerId") String customerId ) {
		return customerService.updateExistingCustomer(customer, customerId);
	}
	
	@GetMapping("/{customerId}/address")
	public Mono<Address> queryCustomerAddress( @PathVariable("customerId") String customerId ) {
		return customerService.queryAddressByCustomerId(customerId);
	}
	
	@GetMapping("")
	public Flux<Customer> getAllCustomer() {
		return customerService.getCustomerList();
	}
	
}
