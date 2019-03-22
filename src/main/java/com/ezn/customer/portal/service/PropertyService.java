package com.ezn.customer.portal.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezn.customer.portal.domain.Property;
import com.ezn.customer.portal.repository.AuthorityRepository;
import com.ezn.customer.portal.repository.PropertyRepository;
import com.ezn.customer.portal.service.dto.PropertyDTO;


@Service
@Transactional
public class PropertyService {

	private final Logger log = LoggerFactory.getLogger(PropertyService.class);

	private final PropertyRepository propertyRepository;
	
    private final AuthorityRepository authorityRepository;

    private final CacheManager cacheManager;
    
    public PropertyService(PropertyRepository propertyRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository, CacheManager cacheManager) {
        this.propertyRepository = propertyRepository; 
    	this.authorityRepository = authorityRepository;
        this.cacheManager = cacheManager;
        
    }
    
    @Transactional(readOnly = false)
    public List<PropertyDTO> getPropertiesByEmail(String email) {
        List<Property> properties = propertyRepository.findPropertiesByEmail(email);
        List<PropertyDTO> propertyDTOS = new ArrayList<PropertyDTO>();
    	for (int i = 0; i < properties.size(); i++) {
    		PropertyDTO dto = new PropertyDTO();
    		BeanUtils.copyProperties(properties.get(i), dto);
    		propertyDTOS.add(dto);
    	}
        
        return propertyDTOS;
    }
    
    public int savePropertyDetails(PropertyDTO propertyDTO) {
    	Property property = new Property();
    	property.setEmail(propertyDTO.getEmail());
    	property.setAddress1(propertyDTO.getAddress1());
    	property.setAddress2(propertyDTO.getAddress2());
    	property.setCity(propertyDTO.getCity());
    	property.setState(propertyDTO.getState());
    	property.setZip(propertyDTO.getZip());
    	property.setCountry(propertyDTO.getCountry());
    	property.setLawnSize(propertyDTO.getLawnSize());
    	property.setPropertySize(propertyDTO.getPropertySize());
    	property.setFloors(propertyDTO.getFloors());
    	property.setPlanType(propertyDTO.getPlanType());
    	property.setActualPrice(propertyDTO.getActualPrice());
    	property.setDiscountPrice(propertyDTO.getDiscountPrice());
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	try {
			property.setServiceStartDate(format.parse(propertyDTO.getServiceStartDate()));
			property.setLastServiceDate(format.parse(propertyDTO.getLastServiceDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	property.setReferralCode(propertyDTO.getReferralCode());
    	property.setCornerLot(propertyDTO.isCornerLot());
    	property.setNotes(propertyDTO.getNotes());
		property.setFlowerBed(propertyDTO.isFlowerBed());
		property.setQuarterlyPestControl(propertyDTO.isQuarterlyPestControl());
    	property.setAgree(propertyDTO.isAgree());
    	property.setParam1(propertyDTO.getParam1());
    	property.setParam2(propertyDTO.getParam2());
    	property.setParam3(propertyDTO.getParam3());
    	property.setParam4(propertyDTO.getParam4());
    	property.setParam5(propertyDTO.getParam5());
    	property.setParam6(propertyDTO.getParam6());
    	property.setParam7(propertyDTO.getParam7());
    	property.setParam8(propertyDTO.getParam8());
    	property.setParam9(propertyDTO.getParam9());
    	property.setParam10(propertyDTO.getParam10());
    	property.setParam11(propertyDTO.getParam11());
    	property.setParam12(propertyDTO.getParam12());
    	property.setParam13(propertyDTO.getParam13());
    	property.setParam14(propertyDTO.getParam14());
    	property.setParam15(propertyDTO.getParam15());
    	property.setParam16(propertyDTO.getParam16());
    	property.setParam17(propertyDTO.getParam17());
    	property.setParam18(propertyDTO.getParam18());
    	property.setParam19(propertyDTO.getParam19());
    	property.setParam20(propertyDTO.getParam20());
    	
    	propertyRepository.save(property);

        log.debug("Saved Property Information for User: {}", property.getEmail());
        return 200;
    }
    
    public Optional<PropertyDTO> updatePropertyDetails(PropertyDTO propertyDTO) {
    	
    	return Optional.of(propertyRepository
                .getOne(propertyDTO.getId()))
                .map(property -> {
                	property.setAddress1(propertyDTO.getAddress1());
			    	property.setAddress2(propertyDTO.getAddress2());
			    	property.setCity(propertyDTO.getCity());
			    	property.setState(propertyDTO.getState());
			    	property.setZip(propertyDTO.getZip());
			    	property.setCountry(propertyDTO.getCountry());
			    	property.setLawnSize(propertyDTO.getLawnSize());
			    	property.setPropertySize(propertyDTO.getPropertySize());
			    	property.setFloors(propertyDTO.getFloors());
			    	property.setPlanId(propertyDTO.getPlanId());
			    	property.setPlanType(propertyDTO.getPlanType());
			    	property.setActualPrice(propertyDTO.getActualPrice());
			    	property.setDiscountPrice(propertyDTO.getDiscountPrice());
			    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			    	String serviceStartDate = propertyDTO.getServiceStartDate();
			    	try {
			    		if(serviceStartDate != null && serviceStartDate.isEmpty())
						property.setServiceStartDate(format.parse(serviceStartDate));
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	String lastServiceDate = propertyDTO.getLastServiceDate();
			    	try {
			    		property.setLastServiceDate(format.parse(lastServiceDate));
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
			    	property.setReferralCode(propertyDTO.getReferralCode());
			    	property.setCornerLot(propertyDTO.isCornerLot());
			    	property.setNotes(propertyDTO.getNotes());
					property.setFlowerBed(propertyDTO.isFlowerBed());
					property.setQuarterlyPestControl(propertyDTO.isQuarterlyPestControl());
			    	property.setAgree(propertyDTO.isAgree());
			    	property.setParam1(propertyDTO.getParam1());
			    	property.setParam2(propertyDTO.getParam2());
			    	property.setParam3(propertyDTO.getParam3());
			    	property.setParam4(propertyDTO.getParam4());
			    	property.setParam5(propertyDTO.getParam5());
			    	property.setParam6(propertyDTO.getParam6());
			    	property.setParam7(propertyDTO.getParam7());
			    	property.setParam8(propertyDTO.getParam8());
			    	property.setParam9(propertyDTO.getParam9());
			    	property.setParam10(propertyDTO.getParam10());
			    	property.setParam11(propertyDTO.getParam11());
			    	property.setParam12(propertyDTO.getParam12());
			    	property.setParam13(propertyDTO.getParam13());
			    	property.setParam14(propertyDTO.getParam14());
			    	property.setParam15(propertyDTO.getParam15());
			    	property.setParam16(propertyDTO.getParam16());
			    	property.setParam17(propertyDTO.getParam17());
			    	property.setParam18(propertyDTO.getParam18());
			    	property.setParam19(propertyDTO.getParam19());
			    	property.setParam20(propertyDTO.getParam20());
			    	return property;
                }).map(PropertyDTO::new);
    	
    }
}
