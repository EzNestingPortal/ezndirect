package com.ezn.customer.portal.web.rest;

import com.ezn.customer.portal.service.StripeService;
import com.ezn.customer.portal.service.dto.StripeCustomer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class StripeResource {

	private static final Logger logger = LoggerFactory.getLogger(StripeResource.class);

	@Autowired
	private StripeCustomer customer;
	
	@Autowired
	private StripeService stripeSvcAdapter;

	
	@PostMapping("/create")
	public ResponseEntity<StripeCustomer> createCustomer(@RequestParam String name, @RequestParam String email, @RequestParam String stripeToken, 
										@RequestParam String addressLine1,  @RequestParam String city, @RequestParam String state) {

		customer.setToken(stripeToken);
		customer.setName(name);
		customer.setEmail(email);
		customer.setAddressLine1(addressLine1);
		customer.setCity(city);
		customer.setState(state);
			
		com.stripe.model.Customer stripeCustomer = stripeSvcAdapter.createCustomer(customer);
		if (null != stripeCustomer) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(500).build();
		}

	}
}