package com.ezn.customer.portal.service.dto;

import java.text.SimpleDateFormat;

import com.ezn.customer.portal.domain.Property;

public class PropertyDTO {
	
	private Long id;
	private int lawnSize;
    private int propertySize;
    private int floors;
    private String planId;
    private String planType;
    private float actualPrice;
    private float discountPrice;
    private String referralCode;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String serviceStartDate;
    private String lastServiceDate;
    private String description;
    private boolean cornerLot;
    private String notes;
	private boolean flowerBed;
	private boolean quarterlyPestControl;
    private boolean agree;
    private String param1;
    private String param2;
    private String param3;
    private String param4;
    private String param5;
    private String param6;
    private String param7;
    private String param8;
    private String param9;
    private String param10;
    private String param11;
    private String param12;
    private String param13;
    private String param14;
    private String param15;
    private String param16;
    private String param17;
    private String param18;
    private String param19;
    private String param20;
    
    public PropertyDTO() {
    	
    }
 
    public PropertyDTO(Property property) {
    	this.setId(property.getId());
    	this.setEmail(property.getEmail());
    	this.setAddress1(property.getAddress1());
    	this.setAddress2(property.getAddress2());
    	this.setCity(property.getCity());
    	this.setState(property.getState());
    	this.setZip(property.getZip());
    	this.setCountry(property.getCountry());
    	this.setLawnSize(property.getLawnSize());
    	this.setPropertySize(property.getPropertySize());
    	this.setFloors(property.getFloors());
    	this.setPlanId(property.getPlanId());
    	this.setPlanType(property.getPlanType());
    	this.setActualPrice(property.getActualPrice());
    	this.setDiscountPrice(property.getDiscountPrice());
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.setServiceStartDate(format.format(property.getServiceStartDate()));
		this.setLastServiceDate(format.format(property.getLastServiceDate()));
    	this.setReferralCode(property.getReferralCode());
    	this.setCornerLot(property.getCornerLot());
    	this.setNotes(property.getNotes());
    	this.setAgree(property.isAgree());
		this.setFlowerBed(property.isFlowerBed()); 
		this.setQuarterlyPestControl(property.isQuarterlyPestControl());
    	this.setParam1(property.getParam1());
    	this.setParam2(property.getParam2());
    	this.setParam3(property.getParam3());
    	this.setParam4(property.getParam4());
    	this.setParam5(property.getParam5());
    	this.setParam6(property.getParam6());
    	this.setParam7(property.getParam7());
    	this.setParam8(property.getParam8());
    	this.setParam9(property.getParam9());
    	this.setParam10(property.getParam10());
    	this.setParam11(property.getParam11());
    	this.setParam12(property.getParam12());
    	this.setParam13(property.getParam13());
    	this.setParam14(property.getParam14());
    	this.setParam15(property.getParam15());
    	this.setParam16(property.getParam16());
    	this.setParam17(property.getParam17());
    	this.setParam18(property.getParam18());
    	this.setParam19(property.getParam19());
    	this.setParam20(property.getParam20());
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLawnSize() {
		return lawnSize;
	}
	
	public void setLawnSize(int lawnSize) {
		this.lawnSize = lawnSize;
	}
	
	public int getPropertySize() {
		return propertySize;
	}
	
	public void setPropertySize(int propertySize) {
		this.propertySize = propertySize;
	}
	
	public int getFloors() {
		return floors;
	}
	
	public void setFloors(int floors) {
		this.floors = floors;
	}
	
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	
	public float getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(float actualPrice) {
		this.actualPrice = actualPrice;
	}

	public float getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getAddress1() {
		return address1;
	}
	
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getAddress2() {
		return address2;
	}
	
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getServiceStartDate() {
		return serviceStartDate;
	}
	
	public void setServiceStartDate(String serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	
	public String getLastServiceDate() {
		return lastServiceDate;
	}
	
	public void setLastServiceDate(String lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}
	
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	
	public boolean isCornerLot() {
		return cornerLot;
	}
	public void setCornerLot(boolean cornerLot) {
		this.cornerLot = cornerLot;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	public boolean isFlowerBed() {
		return flowerBed;
	}

	public void setFlowerBed(boolean flowerBed) {
		this.flowerBed = flowerBed;
	}

	public boolean isQuarterlyPestControl() {
		return quarterlyPestControl;
	}

	public void setQuarterlyPestControl(boolean quarterlyPestControl) {
		this.quarterlyPestControl = quarterlyPestControl;
	}

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public String getParam5() {
		return param5;
	}

	public void setParam5(String param5) {
		this.param5 = param5;
	}

	public String getParam6() {
		return param6;
	}

	public void setParam6(String param6) {
		this.param6 = param6;
	}

	public String getParam7() {
		return param7;
	}

	public void setParam7(String param7) {
		this.param7 = param7;
	}

	public String getParam8() {
		return param8;
	}

	public void setParam8(String param8) {
		this.param8 = param8;
	}

	public String getParam9() {
		return param9;
	}

	public void setParam9(String param9) {
		this.param9 = param9;
	}

	public String getParam10() {
		return param10;
	}

	public void setParam10(String param10) {
		this.param10 = param10;
	}

	public String getParam11() {
		return param11;
	}

	public void setParam11(String param11) {
		this.param11 = param11;
	}

	public String getParam12() {
		return param12;
	}

	public void setParam12(String param12) {
		this.param12 = param12;
	}

	public String getParam13() {
		return param13;
	}

	public void setParam13(String param13) {
		this.param13 = param13;
	}

	public String getParam14() {
		return param14;
	}

	public void setParam14(String param14) {
		this.param14 = param14;
	}

	public String getParam15() {
		return param15;
	}

	public void setParam15(String param15) {
		this.param15 = param15;
	}

	public String getParam16() {
		return param16;
	}

	public void setParam16(String param16) {
		this.param16 = param16;
	}

	public String getParam17() {
		return param17;
	}

	public void setParam17(String param17) {
		this.param17 = param17;
	}

	public String getParam18() {
		return param18;
	}

	public void setParam18(String param18) {
		this.param18 = param18;
	}

	public String getParam19() {
		return param19;
	}

	public void setParam19(String param19) {
		this.param19 = param19;
	}

	public String getParam20() {
		return param20;
	}

	public void setParam20(String param20) {
		this.param20 = param20;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
