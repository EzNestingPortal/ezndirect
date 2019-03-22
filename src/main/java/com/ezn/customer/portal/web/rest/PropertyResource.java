package com.ezn.customer.portal.web.rest;

import io.github.jhipster.web.util.ResponseUtil;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.ezn.customer.portal.repository.PropertyRepository;
import com.ezn.customer.portal.service.PropertyService;
import com.ezn.customer.portal.service.dto.PropertyDTO;
import com.ezn.customer.portal.web.rest.util.HeaderUtil;

/**
 * REST controller for managing pricing.
 */
@RestController
@RequestMapping("/api")
public class PropertyResource {

    private final Logger log = LoggerFactory.getLogger(PropertyResource.class);


    private final PropertyService propertyService;


    public PropertyResource(PropertyService propertyService, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
    }


   /* /**
     * GET /pricing : get pricing.
     *
     * @param property the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all users
     *//*
    @GetMapping("/property")
    public ResponseEntity<Property> getPropertyDetails(HttpServletRequest request) {
        log.debug("in get property details");
        PropertyDTO propertyDTO = new PropertyDTO();
        String email = request.getParameter("email");
        propertyDTO.setEmail(email);
        Optional<Property> property = propertyService.getPropertyDetails(email);

        return ResponseUtil.wrapOrNotFound(property);
    }*/
    
    
    /**
     * GET /users/:login : get the "login" user.
     *
     * @param login the login of the user to find
     * @return the ResponseEntity with status 200 (OK) and with body the "login" user, or with status 404 (Not Found)
     */
    @GetMapping("/property")
    @Timed
    public List<PropertyDTO> getPropertiesByEmail(@RequestParam("email") String email) {
        log.debug("REST request to get property : {}", email);
        
        return propertyService.getPropertiesByEmail(email);
                
    }
    
    @PostMapping("/property") 
    public ResponseEntity<Integer> savePropertyDetails(@Valid @RequestHeader HttpHeaders headers, @Valid @RequestBody PropertyDTO propertyDTO) throws Exception{
        log.debug("in savePropertyDetails");
        
        int status = -1;
        String apiKey = headers.getFirst("api-key");
        if(apiKey == null || apiKey.isEmpty()) {
        	return new ResponseEntity<Integer>(HttpStatus.FORBIDDEN);
        } else if(apiKey.equals("EZN")){
        	status = propertyService.savePropertyDetails(propertyDTO);
        }
                
        if(status == 200)
            return new ResponseEntity<Integer>(HttpStatus.OK);
        else {
            return new ResponseEntity<Integer>(HttpStatus.CONFLICT);
        }    
    }
    
    
    @PutMapping("/property")
    @Timed
    //@Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<PropertyDTO> updatePropertyDetailsr(@Valid @RequestBody PropertyDTO propertyDTO, @Valid @RequestHeader HttpHeaders headers) {
        log.debug("REST request to update PropertyDetails : {}", propertyDTO);
        
        String apiKey = headers.getFirst("api-key");
        if(apiKey == null || apiKey.isEmpty()) {
        	return new ResponseEntity<PropertyDTO>(HttpStatus.FORBIDDEN);
        }
        
        Long id = propertyDTO.getId();
        if(id == null) {
        	return  ResponseEntity.badRequest().headers(HeaderUtil.createAlert("Property Id is required", String.valueOf(id))).build();
        }
        
        Optional<PropertyDTO> updateProperty = propertyService.updatePropertyDetails(propertyDTO);

        return ResponseUtil.wrapOrNotFound(updateProperty,
            HeaderUtil.createAlert("A property is updated with identifier " + propertyDTO.getId(), String.valueOf(propertyDTO.getId())));
    }
}
