package com.ezn.customer.portal.service;

import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.MapperFeature;


import com.ezn.customer.portal.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;


/**
 * Service class for managing users.
 */
@Service
public class PricingService {

    private final Logger log = LoggerFactory.getLogger(PricingService.class);

    private final String LMB = "Twice-monthly Lawn Mowing and Blowing";
    private final String EG = "Edging (twice-monthly)";
    private final String GT = "Grass Trimming (twice-monthly)";
    private final String WE = "Weed-eating (twice-monthly)";
    private final String WT = "Weed Treatment (yearly) + Next One FREE"; 
    private final String LF = "Lawn Fertilizer (yearly) + Next One FREE";
    private final String PRH = "Pre-emergent Herbicides (yearly)";
    private final String POH = "Post-emergent Herbicides (yearly)";
    private final String MUL = "Mulching (yearly)";
    private final String EWC = "Exterior Window Cleaning (yearly)";
    private final String CDP = "Concrete Driveway Powerwashing (yearly)";
    private final String PEW = "Powerwash Entry way (yearly)";
    private final String TCC = "Trash Can Cleaning (yearly)";
    private final String PS = "Powerwash Sidewalks (Yearly)";
    private final String CPP = "Concrete Patio Powerwashing (yearly)";
    private final String CC = "Carpet cleaning (yearly) - 4 rooms only"; 
    private final String IWC = "Inside Window Cleaning (once a year)";
    private final String GC = "Gutter Cleaning (one story or two story)";
    
    private final String F_OA = "FREE Online Account Management and Payments";
    private final String F_WT = "Second Weed Treatment FREE";
    private final String F_LF = "Second Lawn Fertilizer FREE";
    private final String F_OS = "Discount on any Other Services Requested";
    private final String F_FP = "Free Family Photoshoot (yearly)";
    private final String F_RI = "FREE Roof Inspection (Yearly)";
    private final String F_BM = "FREE Body Massage (yearly)";
    private final String F_HT = "FREE Home Technician Visit (yearly)";
    private final String F_HR = "FREE ESTIMATES for HOME REPAIRS";

    private final String BASIC = "Basic Care";
    private final String PRIME = "Prime Care";
    private final String GRAND = "Grand Care";
    private final String ELITE = "Elite Care";
    private static final Map<Integer, Float> basicMap, primeMap, grandMap, eliteMap;
    
    static {
        basicMap = new HashMap<Integer, Float>();
        basicMap.put(1500, 20f);
        basicMap.put(3000, 25f);
        basicMap.put(5000, 25f);
        basicMap.put(6500, 30f);
        basicMap.put(8500, 35f);
        basicMap.put(10000, 40f);
        basicMap.put(11000, 45f);
        basicMap.put(14000, 60f);

       /*  lawnMap = new HashMap<Integer, Float>();
        lawnMap.put(1500, 10f);
        lawnMap.put(3000, 15f);
        lawnMap.put(5000, 15f);
        lawnMap.put(6500, 20f);
        lawnMap.put(8500, 25f);
        lawnMap.put(10000, 30f);
        lawnMap.put(11000, 30f);
        lawnMap.put(14000, 35f); */

        primeMap = new HashMap<Integer, Float>();
        primeMap.put(1500, 20f);
        primeMap.put(2500, 20f);
        primeMap.put(3000, 20f);
        primeMap.put(3500, 20f);
        primeMap.put(4000, 25f);
        primeMap.put(4500, 25f);
        primeMap.put(5000, 30f);
        primeMap.put(6000, 30f);

        grandMap = new HashMap<Integer, Float>();
        grandMap.put(1, 15f);
        grandMap.put(2, 20f);
        grandMap.put(3, 30f);

        eliteMap = new HashMap<Integer, Float>();
        eliteMap.put(1, 15f);
        eliteMap.put(2, 20f);
        eliteMap.put(3, 30f);
        
    }

    public List<SubscriptionDTO> getAvailablePlans(PropertyMetaDataDTO propertyMetaDataDTO){

        List<SubscriptionDTO> subscriptions;
        int lawnSize = propertyMetaDataDTO.getLawnSize();
        int propertySize = propertyMetaDataDTO.getPropertySize();
        int floors = propertyMetaDataDTO.getFloors();

        log.debug("Lawn Size:: " + lawnSize);
        log.debug("Property Size:: " + propertySize);
        log.debug("Floors :: " + floors);

        subscriptions = getSubscriptions(lawnSize, propertySize, floors);
        return subscriptions;

    }

    private Map<String, ServiceDTO> stubServices(){
        Map<String, ServiceDTO> serviceDTOMap = new HashMap<String, ServiceDTO>();
        ServiceDTO serviceDTO = new ServiceDTO();

        serviceDTO.setServiceId("1");
        serviceDTO.setServiceName(LMB);
        serviceDTO.setFrequency("bi-weekly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(LMB, serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("2");
        serviceDTO.setServiceName(EG);
        serviceDTO.setFrequency("bi-weekly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(EG, serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("3");
        serviceDTO.setServiceName(GT);
        serviceDTO.setFrequency("bi-weekly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(GT,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("4");
        serviceDTO.setServiceName(WE);
        serviceDTO.setFrequency("quarterly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(WE,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("5");
        serviceDTO.setServiceName(WT);
        serviceDTO.setFrequency("quarterly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(WT, serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("6");
        serviceDTO.setServiceName(LF);
        serviceDTO.setFrequency("quarterly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(LF, serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("7");
        serviceDTO.setServiceName(PRH);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(PRH,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("8");
        serviceDTO.setServiceName(POH);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(POH,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("9");
        serviceDTO.setServiceName(MUL);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(MUL,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("10");
        serviceDTO.setServiceName(EWC);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(EWC,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("11");
        serviceDTO.setServiceName(CDP);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(CDP,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("12");
        serviceDTO.setServiceName(PEW);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(PEW,serviceDTO);


        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("13");
        serviceDTO.setServiceName(TCC);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(TCC,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("14");
        serviceDTO.setServiceName(PS);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(PS,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("15");
        serviceDTO.setServiceName(CPP);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(CPP,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("16");
        serviceDTO.setServiceName(CC);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(CC,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("17");
        serviceDTO.setServiceName(IWC);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(IWC,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("18");
        serviceDTO.setServiceName(GC);
        serviceDTO.setFrequency("yearly");
        serviceDTO.setCanRequestOneTime(true);
        serviceDTO.setRecurring(true);
        serviceDTO.setType("recurring");
        serviceDTOMap.put(GC,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("19");
        serviceDTO.setServiceName(F_OA);
        serviceDTO.setFree(true);
        serviceDTO.setVisible(false);
        serviceDTOMap.put(F_OA,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("20");
        serviceDTO.setServiceName(F_WT);
        serviceDTO.setFree(true);
        serviceDTO.setVisible(false);
        serviceDTOMap.put(F_WT,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("21");
        serviceDTO.setServiceName(F_LF);
        serviceDTO.setFree(true);
        serviceDTO.setVisible(false);
        serviceDTOMap.put(F_LF,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("22");
        serviceDTO.setServiceName(F_OS);
        serviceDTO.setFree(true);
        serviceDTO.setVisible(false);
        serviceDTOMap.put(F_OS,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("23");
        serviceDTO.setServiceName(F_FP);
        serviceDTO.setFree(true);
        serviceDTO.setVisible(false);
        serviceDTOMap.put(F_FP,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("24");
        serviceDTO.setServiceName(F_RI);
        serviceDTO.setFree(true);
        serviceDTO.setVisible(false);
        serviceDTOMap.put(F_RI,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("25");
        serviceDTO.setServiceName(F_BM);
        serviceDTO.setFree(true);
        serviceDTO.setVisible(false);
        serviceDTOMap.put(F_BM,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("26");
        serviceDTO.setServiceName(F_HT);
        serviceDTO.setFree(true);
        serviceDTO.setVisible(false);
        serviceDTOMap.put(F_HT,serviceDTO);

        serviceDTO = new ServiceDTO();
        serviceDTO.setServiceId("27");
        serviceDTO.setServiceName(F_HR);
        serviceDTO.setFree(true);
        serviceDTO.setVisible(false);
        serviceDTOMap.put(F_HR,serviceDTO);
        

        return serviceDTOMap;
    }

    private List<SubscriptionDTO> getSubscriptions(Integer lawnSize, Integer propertySize, Integer floors) {

        SubscriptionDTO subscriptionDTO = null;
        List<SubscriptionDTO> subscriptionDTOList = new ArrayList<SubscriptionDTO>();
        Map<String, ServiceDTO> serviceDTOMap = stubServices();
        List<AvailableServiceDTO> serviceDTOList = new ArrayList<AvailableServiceDTO>();

        Float price =  basicMap.get(lawnSize);
        
        

        subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setSubscriptionId("1001");
        subscriptionDTO.setSubscriptionName(BASIC);
        subscriptionDTO.setFrequency("bi-weekly");
        subscriptionDTO.setIdleMonths("Dec & Jan");
        subscriptionDTO.setYearlySavings(85.00);
        subscriptionDTO.setPromoCode("NH");
        subscriptionDTO.setDiscountedPrice(price);
        subscriptionDTO.setOriginalAmount(30.00);
        subscriptionDTO.setFreeDiscount(0f);
        serviceDTOList = new ArrayList<AvailableServiceDTO>();

        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_OA), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_WT), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_LF), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_OS), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_FP), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_RI), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_BM), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_HT), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_HR), false));

        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(LMB), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(EG), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(GT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(WE), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(WT), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(LF), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PRH), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(POH), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(MUL), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(EWC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CDP), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PEW), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(TCC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PS), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CPP), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(IWC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(GC), false));

        
        

        subscriptionDTO.setServices(serviceDTOList);
        subscriptionDTOList.add(subscriptionDTO);
        
        /*
        price = price + lawnMap.get(lawnSize);

        subscriptionDTO=new SubscriptionDTO();
        subscriptionDTO.setSubscriptionId("1002");
        subscriptionDTO.setSubscriptionName(LAWN);
        subscriptionDTO.setFrequency("bi-weekly");
        subscriptionDTO.setIdleMonths("Dec and Jan");
        subscriptionDTO.setYearlySavings(345.00);
        subscriptionDTO.setPromoCode("NH");
        subscriptionDTO.setDiscountedPrice(price);
        subscriptionDTO.setOriginalAmount(60.00);
        serviceDTOList = new ArrayList<AvailableServiceDTO>();
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(LMB), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(EGT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(WE), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(LF), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(WC), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(EWC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(TCC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CC), false));
        subscriptionDTO.setServices(serviceDTOList);
        subscriptionDTOList.add(subscriptionDTO); */

        price = price + primeMap.get(propertySize);

        subscriptionDTO=new SubscriptionDTO();
        subscriptionDTO.setSubscriptionId("1002");
        subscriptionDTO.setSubscriptionName(PRIME);
        subscriptionDTO.setFrequency("bi-weekly");
        subscriptionDTO.setIdleMonths("Dec & Jan");
        subscriptionDTO.setYearlySavings(480.00);
        subscriptionDTO.setPromoCode("NH");
        subscriptionDTO.setDiscountedPrice(price);
        subscriptionDTO.setOriginalAmount(70.00);
        subscriptionDTO.setFreeDiscount(5f);
        serviceDTOList = new ArrayList<AvailableServiceDTO>();
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_OA), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_WT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_LF), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_OS), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_FP), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_RI), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_BM), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_HT), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_HR), false));

        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(LMB), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(EG), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(GT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(WE), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(WT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(LF), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PRH), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(POH), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(MUL), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(EWC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CDP), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PEW), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(TCC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PS), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CPP), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(IWC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(GC), false));
        
        subscriptionDTO.setServices(serviceDTOList);
        subscriptionDTOList.add(subscriptionDTO);

        price = price + grandMap.get(floors);

        subscriptionDTO=new SubscriptionDTO();
        subscriptionDTO.setSubscriptionId("1003");
        subscriptionDTO.setSubscriptionName(GRAND);
        subscriptionDTO.setFrequency("bi-weekly");
        subscriptionDTO.setIdleMonths("Dec & Jan");
        subscriptionDTO.setYearlySavings(750.00);
        subscriptionDTO.setPromoCode("NH");
        subscriptionDTO.setDiscountedPrice(price);
        subscriptionDTO.setOriginalAmount(100.00);
        subscriptionDTO.setFreeDiscount(10f);
        serviceDTOList = new ArrayList<AvailableServiceDTO>();
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_OA), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_WT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_LF), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_OS), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_FP), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_RI), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_BM), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_HT), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_HR), false));

        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(LMB), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(EG), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(GT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(WE), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(WT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(LF), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PRH), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(POH), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(MUL), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(EWC), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CDP), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PEW), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(TCC), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PS), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CPP), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(IWC), false));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(GC), false));

       
        subscriptionDTO.setServices(serviceDTOList);
        subscriptionDTOList.add(subscriptionDTO);

        price = price + eliteMap.get(floors);

        subscriptionDTO=new SubscriptionDTO();
        subscriptionDTO.setSubscriptionId("1004");
        subscriptionDTO.setSubscriptionName(ELITE);
        subscriptionDTO.setFrequency("bi-weekly");
        subscriptionDTO.setIdleMonths("Dec & Jan");
        subscriptionDTO.setYearlySavings(750.00);
        subscriptionDTO.setPromoCode("NH");
        subscriptionDTO.setDiscountedPrice(price);
        subscriptionDTO.setOriginalAmount(100.00);
        subscriptionDTO.setFreeDiscount(15f);
        serviceDTOList = new ArrayList<AvailableServiceDTO>();
        
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_OA), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_WT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_LF), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_OS), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_FP), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_RI), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_BM), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_HT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(F_HR), true));

        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(LMB), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(EG), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(GT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(WE), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(WT), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(LF), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PRH), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(POH), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(MUL), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(EWC), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CDP), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PEW), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(TCC), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(PS), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CPP), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(CC), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(IWC), true));
        serviceDTOList.add(new AvailableServiceDTO(serviceDTOMap.get(GC), true));

        subscriptionDTO.setServices(serviceDTOList);
        subscriptionDTOList.add(subscriptionDTO);

        return subscriptionDTOList;
    }

    public Integer enroll(UserDTO userDTO) throws Exception{
        
        String displayName = userDTO.getFirstName() +  " " + userDTO.getLastName();


        String url="https://Adeagles.0.razorsync.com/ApiService.svc/Customer/Create";
        URL object=new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Host", "AdEagles.0.razorsync.com");
        con.setRequestProperty("ServerName", "AdEagles");
        con.setRequestProperty("Token", "f61ab5c0-77b4-4115-8e5a-0607c856baa6");
        con.setRequestMethod("POST");

        Customer customer = new Customer();
        String uuid = UUID.randomUUID().toString();

        customer.setDisplayName(displayName + " " + System.currentTimeMillis());
        customer.setCustomId(uuid);

        Contacts contact= new Contacts();
        contact.setFirstName(userDTO.getFirstName());
        contact.setLastName(userDTO.getLastName());
        contact.setMobilePhone(userDTO.getMobileNo());
        contact.setEmail(userDTO.getEmail());

        Contacts[] contacts = new Contacts[1];
        contacts[0] = contact;

        customer.setContacts(contacts);

        Addresses address = new Addresses();
        address.setAddressLine1(userDTO.getProperties().get(0).getAddress1());
        String address2 = userDTO.getProperties().get(0).getAddress2();
        System.out.println(address2);
        if(null == address2 || "".equals(address2))  {
            address2 = "Dummy Address Line 2";
        }

        address.setAddressLine2(address2);
        String description2 = userDTO.getProperties().get(0).getCity() + "|" + userDTO.getProperties().get(0).getState() + "|" 
        					+ userDTO.getProperties().get(0).getZip()  + "|" + userDTO.getProperties().get(0).getServiceStartDate() + "|" 
        					+ userDTO.getProperties().get(0).getLastServiceDate()  + "|" + userDTO.getProperties().get(0).getNotes();
        address.setDescription(userDTO.getProperties().get(0).getDescription() + "|" + description2);
        Addresses[] addresses = {address};

        customer.setAddresses(addresses);

        ObjectMapper mapper = new ObjectMapper();
       mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
       
       //Object to JSON in String
        String payload = mapper.writeValueAsString(customer);

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(payload);
        wr.flush();

        //display what returns the POST request

        StringBuilder sb = new StringBuilder();  
        int HttpResult = con.getResponseCode();
        log.debug("Status:: " + HttpResult); 
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;  
            while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }
            br.close();
            //System.out.println("IF =>" + sb.toString());  
        } else {
           log.debug("ERROR =>" + HttpResult + "\n" + con.getResponseMessage());  
        }  

        return HttpResult;
    }
}
