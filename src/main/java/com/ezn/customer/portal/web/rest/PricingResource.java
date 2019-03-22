package com.ezn.customer.portal.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezn.customer.portal.service.PricingService;
import com.ezn.customer.portal.service.dto.PropertyMetaDataDTO;
import com.ezn.customer.portal.service.dto.SubscriptionDTO;
import com.ezn.customer.portal.service.dto.UserDTO;

/**
 * REST controller for managing pricing.
 */
@RestController
@RequestMapping("/api")
public class PricingResource {

    private final Logger log = LoggerFactory.getLogger(PricingResource.class);


    private final PricingService pricingService;
    
    public PricingResource(PricingService pricingService) {
        this.pricingService = pricingService;
    }


    /**
     * GET /pricing : get pricing.
     *
     * @param property the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all users
     */
    @GetMapping("/pricing")
    public ResponseEntity<List<SubscriptionDTO>> getPricing(HttpServletRequest request) {
        log.debug("in get pricing");
        PropertyMetaDataDTO propertyMetaDataDTO = new PropertyMetaDataDTO();
        propertyMetaDataDTO.setLawnSize(Integer.parseInt(request.getParameter("lawnSize")));
        propertyMetaDataDTO.setPropertySize(Integer.parseInt(request.getParameter("propertySize")));
        propertyMetaDataDTO.setFloors(Integer.parseInt(request.getParameter("floors")));

        return new ResponseEntity<>(pricingService.getAvailablePlans(propertyMetaDataDTO),HttpStatus.OK);
    }

    @PostMapping("/enroll") 
    public ResponseEntity enroll(@Valid @RequestBody UserDTO userDTO) throws Exception{
        log.debug("in enroll");
        int status = pricingService.enroll(userDTO);
        
        if(status == 200)
            return new ResponseEntity(HttpStatus.OK);
        else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }    
    }
}
