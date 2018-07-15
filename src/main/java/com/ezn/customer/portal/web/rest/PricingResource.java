package com.ezn.customer.portal.web.rest;

import com.ezn.customer.portal.security.AuthoritiesConstants;
import com.ezn.customer.portal.service.PricingService;
import com.ezn.customer.portal.service.dto.PropertyMetaDataDTO;
import com.ezn.customer.portal.service.dto.SubscriptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
        propertyMetaDataDTO.setLotSize(Integer.parseInt(request.getParameter("lotSize")));
        propertyMetaDataDTO.setFloorSize(Integer.parseInt(request.getParameter("floorSize")));
        propertyMetaDataDTO.setFloors(Integer.parseInt(request.getParameter("floors")));



        return new ResponseEntity<>(pricingService.getAvailablePlans(propertyMetaDataDTO),HttpStatus.OK);
    }




//
//    /**
//     * GET /pricing : get pricing.
//     *
//     * @param property the pagination information
//     * @return the ResponseEntity with status 200 (OK) and with body all users
//     */
//    @GetMapping("/pricing")
//    @Timed
//    @Secured(AuthoritiesConstants.ADMIN)
//    public ResponseEntity<List<Pricing>> getPricing(Property property) {
//        final Page<UserDTO> page = pricingService.getAllManagedUsers(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    }
//
//
//    /**
//     * GET /pricing : get pricing.
//     *
//     * @param r the pagination information
//     * @return the ResponseEntity with status 200 (OK) and with body all users
//     */
//    @GetMapping("/pricing")
//    @Timed
//    @Secured(AuthoritiesConstants.ADMIN)
//    public ResponseEntity<List<UserDTO>> getPricing(Property property) {
//        final Page<UserDTO> page = userService.getAllManagedUsers(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    }



}
