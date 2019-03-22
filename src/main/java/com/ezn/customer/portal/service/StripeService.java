package com.ezn.customer.portal.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.ezn.customer.portal.service.dto.StripeCustomer;
import com.stripe.Stripe;
import com.stripe.model.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StripeService {


	private static final Logger logger = LoggerFactory
			.getLogger(StripeService.class);
	
	private static final String stripeSecretKey = "pk_test_QseBPReeIxUM1y6tvGHv3VYL";

	@PostConstruct
	private void init() {
		Stripe.apiKey = stripeSecretKey;

	}

	/**
	 * 
	 * @param customer
	 * @return
	 */
	public Customer createCustomer(StripeCustomer stripeCustomer) {
		logger.debug("entering createCustomer ");

		Stripe.apiKey = "sk_test_gl11HKrUumTX4NAmvoxeZsLL";

		Map<String, Object> customerParams = new HashMap<String, Object>();

		customerParams.put("source", stripeCustomer.getToken());
		customerParams.put("email", stripeCustomer.getEmail());
		Map<String, String> metadata = new HashMap<String, String>();
		metadata.put("name", stripeCustomer.getName());
		
		String addressLine1 = stripeCustomer.getAddressLine1();
		if(addressLine1 != null && !addressLine1.isEmpty()) {
			metadata.put("addressLine1", addressLine1);
		}
		
		String city = stripeCustomer.getCity();
		if(city != null && !city.isEmpty()) {
			metadata.put("city", city);
		}
		
		String state = stripeCustomer.getState();
		if(state != null && !state.isEmpty()) {
			metadata.put("state", state);
		}
		
		//metadata.put("country", stripeCustomer.getCountry());
		//metadata.put("addressLine2", stripeCustomer.getAddressLine2());
		//metadata.put("zip", stripeCustomer.getZip());
		//metadata.put("phone", stripeCustomer.getMobilePhone()); 
      
		customerParams.put("metadata", metadata);

		Customer cust = null;
		try {
			cust = Customer.create(customerParams);

		} catch ( Exception e ) {
			e.printStackTrace();
		}
		logger.debug("exiting createCustomer ");
		return cust;

	}

	

}
