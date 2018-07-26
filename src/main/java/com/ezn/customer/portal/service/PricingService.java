package com.ezn.customer.portal.service;

import com.ezn.customer.portal.config.Constants;
import com.ezn.customer.portal.domain.Authority;
import com.ezn.customer.portal.domain.User;
import com.ezn.customer.portal.repository.AuthorityRepository;
import com.ezn.customer.portal.repository.UserRepository;
import com.ezn.customer.portal.security.AuthoritiesConstants;
import com.ezn.customer.portal.security.SecurityUtils;
import com.ezn.customer.portal.service.dto.*;
import com.ezn.customer.portal.service.util.RandomUtil;
import com.ezn.customer.portal.web.rest.errors.InvalidPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
public class PricingService {

    private final Logger log = LoggerFactory.getLogger(PricingService.class);

    private final String LMB = "Lawn Mowing and Blowing";
    private final String EGT = "Edging and Grass Trimming";
    private final String PC = "Pest Control";
    private final String TCC = "Trash Can Cleaning";
    private final String EWC = "Exterior Window Cleaning";
    private final String WE = "Weed Eating";
    private final String LF = "Lawn Fertilizer";
    private final String CC = "Carpet cleaning";
    private final String WC = "Weed Control";

    private final String BASIC = "Basic Care";
    private final String LAWN = "Lawn Care";
    private final String PRIME = "Prime Care";
    private final String GRAND = "Grand Care";



    public List<SubscriptionDTO> getAvailablePlans(PropertyMetaDataDTO propertyMetaDataDTO){

        String planInitialized = initializePlan(propertyMetaDataDTO);
        List<SubscriptionDTO> subscriptionDTO;

        switch(planInitialized){
            case "A" :
                subscriptionDTO = getSubscriptionDTO(planInitialized);
                break;
            case "B" :
                subscriptionDTO = getSubscriptionDTO(planInitialized);
                break;
            case "C" :
                subscriptionDTO = getSubscriptionDTO("O");
                break;
            case "O" :
                subscriptionDTO = getSubscriptionDTO("O");
                break;
            default :
                subscriptionDTO = getSubscriptionDTO("O");
                break;
        }

        return subscriptionDTO;

    }

    private Map<String, ServiceDTO> stubServices(){
        Map<String, ServiceDTO> serviceDTOMap = new HashMap<String, ServiceDTO>();
        ServiceDTO serviceDTO = new ServiceDTO();

        serviceDTO.setServiceId("1");
        serviceDTO.setServiceName(LMB);
        serviceDTO.setFrequency("bimonthly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");

        serviceDTOMap.put(LMB, serviceDTO);
        serviceDTO = new ServiceDTO();

        serviceDTO.setServiceId("2");
        serviceDTO.setServiceName(EGT);
        serviceDTO.setFrequency("bimonthly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");

        serviceDTOMap.put(EGT, serviceDTO);
        serviceDTO = new ServiceDTO();

        serviceDTO.setServiceId("3");
        serviceDTO.setServiceName(WE);
        serviceDTO.setFrequency("bimonthly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");

        serviceDTOMap.put(WE,serviceDTO);
        serviceDTO = new ServiceDTO();

        serviceDTO.setServiceId("4");
        serviceDTO.setServiceName(LF);
        serviceDTO.setFrequency("quarterly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");

        serviceDTOMap.put(LF,serviceDTO);
        serviceDTO = new ServiceDTO();

        serviceDTO.setServiceId("5");
        serviceDTO.setServiceName("Weed control");
        serviceDTO.setFrequency("quarterly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");

        serviceDTOMap.put(WC, serviceDTO);
        serviceDTO = new ServiceDTO();

        serviceDTO.setServiceId("6");
        serviceDTO.setServiceName(PC);
        serviceDTO.setFrequency("quarterly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");

        serviceDTOMap.put(PC, serviceDTO);
        serviceDTO = new ServiceDTO();

        serviceDTO.setServiceId("7");
        serviceDTO.setServiceName(EWC);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");

        serviceDTOMap.put(EWC,serviceDTO);
        serviceDTO = new ServiceDTO();

        serviceDTO.setServiceId("8");
        serviceDTO.setServiceName(TCC);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");

        serviceDTOMap.put(TCC,serviceDTO);
        serviceDTO = new ServiceDTO();

        serviceDTO.setServiceId("9");
        serviceDTO.setServiceName(CC);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");

        serviceDTOMap.put(CC,serviceDTO);

        return serviceDTOMap;

    }

    private List<SubscriptionDTO> getSubscriptionDTO(String planInitialized) {

        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        List<SubscriptionDTO> subscriptionDTOList = new ArrayList<SubscriptionDTO>();
        Map<String, ServiceDTO> serviceDTOMap = stubServices();
        List<ServiceDTO> serviceDTOList = new ArrayList<ServiceDTO>();

        if (planInitialized.equals("A")) {
            subscriptionDTO.setSubscriptionId("1001");
            subscriptionDTO.setSubscriptionName("BASIC");
            subscriptionDTO.setFrequency("biweekly");
            subscriptionDTO.setIdleMonths("Dec, Jan, Feb");
            subscriptionDTO.setYearlySavings(85.00);
            subscriptionDTO.setPromoCode("NH");
            subscriptionDTO.setDiscountedPrice(25.00);
            subscriptionDTO.setOriginalAmount(30.00);
            serviceDTOList = new ArrayList<ServiceDTO>();
            serviceDTOList.add(serviceDTOMap.get(LMB));
            serviceDTOList.add(serviceDTOMap.get(EGT));
            subscriptionDTO.setServices(serviceDTOList);
            subscriptionDTOList.add(subscriptionDTO);
            subscriptionDTO=new SubscriptionDTO();


            subscriptionDTO.setSubscriptionId("1002");
            subscriptionDTO.setSubscriptionName("LAWN");
            subscriptionDTO.setFrequency("biweekly");
            subscriptionDTO.setIdleMonths("Dec, Jan, Feb");
            subscriptionDTO.setYearlySavings(345.00);
            subscriptionDTO.setPromoCode("NH");
            subscriptionDTO.setDiscountedPrice(40.00);
            subscriptionDTO.setOriginalAmount(60.00);
            serviceDTOList = new ArrayList<ServiceDTO>();
            serviceDTOList.add(serviceDTOMap.get(LMB));
            serviceDTOList.add(serviceDTOMap.get(EGT));
            serviceDTOList.add(serviceDTOMap.get(WE));
            serviceDTOList.add(serviceDTOMap.get(LF));
            serviceDTOList.add(serviceDTOMap.get(WC));
            subscriptionDTO.setServices(serviceDTOList);
            subscriptionDTOList.add(subscriptionDTO);
            subscriptionDTO=new SubscriptionDTO();


            subscriptionDTO.setSubscriptionId("1003");
            subscriptionDTO.setSubscriptionName(PRIME);
            subscriptionDTO.setFrequency("biweekly");
            subscriptionDTO.setIdleMonths("Dec, Jan, Feb");
            subscriptionDTO.setYearlySavings(480.00);
            subscriptionDTO.setPromoCode("NH");
            subscriptionDTO.setDiscountedPrice(50.00);
            subscriptionDTO.setOriginalAmount(70.00);
            serviceDTOList = new ArrayList<ServiceDTO>();
            serviceDTOList.add(serviceDTOMap.get(LMB));
            serviceDTOList.add(serviceDTOMap.get(EGT));
            serviceDTOList.add(serviceDTOMap.get(WE));
            serviceDTOList.add(serviceDTOMap.get(LF));
            serviceDTOList.add(serviceDTOMap.get(WC));
            serviceDTOList.add(serviceDTOMap.get(PC));
            subscriptionDTO.setServices(serviceDTOList);
            subscriptionDTOList.add(subscriptionDTO);
            subscriptionDTO=new SubscriptionDTO();


            subscriptionDTO.setSubscriptionId("1004");
            subscriptionDTO.setSubscriptionName(GRAND);
            subscriptionDTO.setFrequency("biweekly");
            subscriptionDTO.setIdleMonths("Dec, Jan, Feb");
            subscriptionDTO.setYearlySavings(750.00);
            subscriptionDTO.setPromoCode("NH");
            subscriptionDTO.setDiscountedPrice(65.00);
            subscriptionDTO.setOriginalAmount(100.00);
            serviceDTOList = new ArrayList<ServiceDTO>();
            serviceDTOList.add(serviceDTOMap.get(LMB));
            serviceDTOList.add(serviceDTOMap.get(EGT));
            serviceDTOList.add(serviceDTOMap.get(WE));
            serviceDTOList.add(serviceDTOMap.get(LF));
            serviceDTOList.add(serviceDTOMap.get(WC));
            serviceDTOList.add(serviceDTOMap.get(PC));
            serviceDTOList.add(serviceDTOMap.get(EWC));
            serviceDTOList.add(serviceDTOMap.get(TCC));
            serviceDTOList.add(serviceDTOMap.get(CC));
            subscriptionDTO.setServices(serviceDTOList);
            subscriptionDTOList.add(subscriptionDTO);
            subscriptionDTO=new SubscriptionDTO();


        } else if(planInitialized.equals("B")) {
            subscriptionDTO.setSubscriptionId("1001");
            subscriptionDTO.setSubscriptionName("BASIC");
            subscriptionDTO.setFrequency("biweekly");
            subscriptionDTO.setIdleMonths("Dec, Jan, Feb");
            subscriptionDTO.setYearlySavings(125.00);
            subscriptionDTO.setPromoCode("NH");
            subscriptionDTO.setDiscountedPrice(40.00);
            subscriptionDTO.setOriginalAmount(50.00);
            serviceDTOList = new ArrayList<ServiceDTO>();
            serviceDTOList.add(serviceDTOMap.get(LMB));
            serviceDTOList.add(serviceDTOMap.get(EGT));
            subscriptionDTO.setServices(serviceDTOList);
            subscriptionDTOList.add(subscriptionDTO);
            subscriptionDTO=new SubscriptionDTO();


            subscriptionDTO.setSubscriptionId("1002");
            subscriptionDTO.setSubscriptionName("LAWN");
            subscriptionDTO.setFrequency("biweekly");
            subscriptionDTO.setIdleMonths("Dec, Jan, Feb");
            subscriptionDTO.setYearlySavings(520.00);
            subscriptionDTO.setPromoCode("NH");
            subscriptionDTO.setDiscountedPrice(55.00);
            subscriptionDTO.setOriginalAmount(68.00);
            serviceDTOList = new ArrayList<ServiceDTO>();
            serviceDTOList.add(serviceDTOMap.get(LMB));
            serviceDTOList.add(serviceDTOMap.get(EGT));
            serviceDTOList.add(serviceDTOMap.get(WE));
            serviceDTOList.add(serviceDTOMap.get(LF));
            serviceDTOList.add(serviceDTOMap.get(WC));
            subscriptionDTO.setServices(serviceDTOList);
            subscriptionDTOList.add(subscriptionDTO);
            subscriptionDTO=new SubscriptionDTO();

            subscriptionDTO.setSubscriptionId("1003");
            subscriptionDTO.setSubscriptionName(PRIME);
            subscriptionDTO.setFrequency("biweekly");
            subscriptionDTO.setIdleMonths("Dec, Jan, Feb");
            subscriptionDTO.setYearlySavings(720.00);
            subscriptionDTO.setPromoCode("NH");
            subscriptionDTO.setDiscountedPrice(75.00);
            subscriptionDTO.setOriginalAmount(100.00);
            serviceDTOList = new ArrayList<ServiceDTO>();
            serviceDTOList.add(serviceDTOMap.get(LMB));
            serviceDTOList.add(serviceDTOMap.get(EGT));
            serviceDTOList.add(serviceDTOMap.get(WE));
            serviceDTOList.add(serviceDTOMap.get(LF));
            serviceDTOList.add(serviceDTOMap.get(WC));
            serviceDTOList.add(serviceDTOMap.get(PC));
            subscriptionDTO.setServices(serviceDTOList);
            subscriptionDTOList.add(subscriptionDTO);
            subscriptionDTO=new SubscriptionDTO();


            subscriptionDTO.setSubscriptionId("1004");
            subscriptionDTO.setSubscriptionName(GRAND);
            subscriptionDTO.setFrequency("biweekly");
            subscriptionDTO.setIdleMonths("Dec, Jan, Feb");
            subscriptionDTO.setYearlySavings(1125.00);
            subscriptionDTO.setPromoCode("NH");
            subscriptionDTO.setDiscountedPrice(85.00);
            subscriptionDTO.setOriginalAmount(150.00);
            serviceDTOList = new ArrayList<ServiceDTO>();
            serviceDTOList.add(serviceDTOMap.get(LMB));
            serviceDTOList.add(serviceDTOMap.get(EGT));
            serviceDTOList.add(serviceDTOMap.get(WE));
            serviceDTOList.add(serviceDTOMap.get(LF));
            serviceDTOList.add(serviceDTOMap.get(WC));
            serviceDTOList.add(serviceDTOMap.get(PC));
            serviceDTOList.add(serviceDTOMap.get(EWC));
            serviceDTOList.add(serviceDTOMap.get(TCC));
            serviceDTOList.add(serviceDTOMap.get(CC));
            subscriptionDTO.setServices(serviceDTOList);
            subscriptionDTOList.add(subscriptionDTO);
            subscriptionDTO=new SubscriptionDTO();

        }

        return subscriptionDTOList;


    }

    private String initializePlan(PropertyMetaDataDTO propertyMetaDataDTO) {
        log.debug("Lot Size:: " + propertyMetaDataDTO.getLotSize());
        log.debug("Floor Size:: " + propertyMetaDataDTO.getFloorSize());
        log.debug("Floors :: " + propertyMetaDataDTO.getFloors());


        int size = propertyMetaDataDTO.getLotSize() - propertyMetaDataDTO.getFloorSize();
        if(propertyMetaDataDTO.getFloors()>1){
            size = (int) 0.7 * size;
        }

            String planInitialized = null;

            if(size<=5000){
                planInitialized = "A";
            } else if (size>5000 && size<=10000) {
                planInitialized = "B";
            } else if (size >10000 && size <=15000){
                planInitialized = "C";
            } else {
                planInitialized = "O";
            }

        return planInitialized;
    }


}
