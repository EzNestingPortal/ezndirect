package com.ezn.customer.portal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezn.customer.portal.domain.User;
import com.ezn.customer.portal.repository.UserRepository;
import com.ezn.customer.portal.service.dto.StripeCustomer;
import com.ezn.customer.portal.web.rest.errors.DuplicateCreditCardException;
import com.stripe.Stripe;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.model.PaymentSource;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;

@Service
public class StripeService {


	private static final Logger logger = LoggerFactory
			.getLogger(StripeService.class);
	
	private static final String stripeSecretKey = "pk_test_QseBPReeIxUM1y6tvGHv3VYL";
	
	
	@Autowired
	private UserRepository userRepository;

	private String uuid;

	@PostConstruct
	private void init() {
		Stripe.apiKey = stripeSecretKey;
		uuid = UUID.randomUUID().toString();
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
		String token =  stripeCustomer.getToken();
		String email = stripeCustomer.getEmail();
		customerParams.put("source", token);
		customerParams.put("email", email);
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
		
	
		RequestOptions options = RequestOptions
		.builder()
		.setIdempotencyKey(uuid)
		.build();
		logger.debug("UUID :: " + uuid); 
		
		
		Customer cust = null;
		boolean cardExists = false;
		try {
			Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(email);

			if(existingUser.get().getCustomerStripeId() == null) {
				logger.debug("New Customer creation");
				cust = Customer.create(customerParams);
			} else {
				Map<String, Object> params = new HashMap<String, Object>();
				CustomerCollection customerCollection = Customer.list(params);
					List<Customer> customerList = customerCollection.getData();
					int i = 0;
					for (Customer customer : customerList) {
						logger.debug("customer " + i++);
						String dbEmail  = customer.getEmail();
						if(dbEmail.equalsIgnoreCase(email)) {
							logger.debug("Adding a differnt card");
							params.put("source", token);
							String customerStripeId = customer.getId();
							cust = Customer.retrieve(customerStripeId);
							String fingerPrint = Token.retrieve(token).getCard().getFingerprint();
							
							List<PaymentSource> paymentSourceList = cust.getSources().getData();
				
							if(paymentSourceList != null) {
								for(PaymentSource source: paymentSourceList) {
									Card card = (Card) source;
									String existingFingerPrint = card.getFingerprint();
									
									if(existingFingerPrint != null && existingFingerPrint.equals(fingerPrint) ) {
										cardExists = true;
									}
								}
							}
							if(!cardExists) {
								cust.getSources().create(params);
								break;
							} else {
								throw new DuplicateCreditCardException(); 
							}
						}
					}
				}
		} catch(InvalidRequestException ire) {
			logger.error("Error occurred in StripeService - InvalidReequestException", ire);
		}
		catch ( Exception e ) {
			logger.error("Error occurred in StripeService - Exception", e);
			if(cardExists) {
				throw new DuplicateCreditCardException();
			}
		}
		logger.debug("exiting createCustomer ");
		return cust;

	}

}
