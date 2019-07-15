package com.ezn.customer.portal.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A user.
 */
@Entity
@Table(name = "ezn_property")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Email
    @NotNull
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String email;
    
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address_line1", length = 100, nullable = false)
    private String address1;
    
    @Size(max = 100)
    @Column(name = "address_line2", length = 100)
    private String address2;

    @NotNull
    @Size(max = 50)
    @Column(name = "city", length = 50)
    private String city;

    @NotNull
    @Size(max = 50)
    @Column(name = "state", length = 50)
    private String state;
    
    @NotNull
    @Size(max = 5)
    @Column(name = "zip_code", length = 5)
    private String zip;
    
    @NotNull
    @Size(max = 50)
    @Column(name = "country", length = 50)
    private String country;
    
    
    @NotNull
    @Column(name = "lawn_size", length = 50)
    private int lawnSize;
    
    @NotNull
    @Column(name = "property_size", length = 50)
    private int propertySize;
    
    @NotNull
    @Column(name = "floors", length = 50)
    private int floors;
    
    @NotNull
    @Column(name = "plan_id", length = 10)
    private String planId;
    
    @NotNull
    @Column(name = "plan_type", length = 10)
    private String planType;
    
    @NotNull
    @Column(name = "actual_price")
    private float actualPrice;
    
    @NotNull
    @Column(name = "discount_price")
    private float discountPrice;
    
    @NotNull
    @Column(name = "service_start_date", length = 50)
    private Date serviceStartDate;
    
    @Column(name = "last_service_date", length = 50)
    private Date lastServiceDate;
    
    @Size(max = 50)
    @Column(name = "referral_code", length = 50)
    private String referralCode;
        
    @NotNull
    @Column(name = "corner_lot")
    private boolean cornerLot;
    
    @Size(max = 500)
    @Column(name = "notes", length = 500)
    private String notes;
    
    @NotNull
    @Column(name = "flower_bed")
	private boolean flowerBed;
	
	@NotNull
    @Column(name = "quarterly_pest_control")
    private boolean quarterlyPestControl;
    
    @NotNull
    @Column(name = "agree")
    private boolean agree;
    
    @NotNull
    @Column(name = "agreement_date", length = 50)
    private Instant agreementDate = Instant.now();
    
    @Size(max = 50)
    @Column(name = "param1", length = 50)
    private String param1;
    
    @Size(max = 50)
    @Column(name = "param2", length = 50)
    private String param2;
    
    @Size(max = 50)
    @Column(name = "param3", length = 50)
    private String param3;
    
    @Size(max = 50)
    @Column(name = "param4", length = 50)
    private String param4;
    
    @Size(max = 50)
    @Column(name = "param5", length = 50)
    private String param5;
    
    @Size(max = 50)
    @Column(name = "param6", length = 50)
    private String param6;
    
    @Size(max = 50)
    @Column(name = "param7", length = 50)
    private String param7;
    
    @Size(max = 50)
    @Column(name = "param8", length = 50)
    private String param8;
    
    @Size(max = 50)
    @Column(name = "param9", length = 50)
    private String param9;
    
    @Size(max = 50)
    @Column(name = "param10", length = 50)
    private String param10;
    
    @Size(max = 50)
    @Column(name = "param11", length = 50)
    private String param11;
    
    @Size(max = 50)
    @Column(name = "param12", length = 50)
    private String param12;
    
    @Size(max = 50)
    @Column(name = "param13", length = 50)
    private String param13;
    
    @Size(max = 50)
    @Column(name = "param14", length = 50)
    private String param14;
    
    @Size(max = 50)
    @Column(name = "param15", length = 50)
    private String param15;
    
    @Size(max = 50)
    @Column(name = "param16", length = 50)
    private String param16;
    
    @Size(max = 50)
    @Column(name = "param17", length = 50)
    private String param17;
    
    @Size(max = 50)
    @Column(name = "param18", length = 50)
    private String param18;
    
    @Size(max = 50)
    @Column(name = "param19", length = 50)
    private String param19;
    
    @Size(max = 50)
    @Column(name = "param20", length = 50)
    private String param20;
    
    
    public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Date getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public Date getLastServiceDate() {
		return lastServiceDate;
	}

	public void setLastServiceDate(Date lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public boolean getCornerLot() {
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

	public Instant getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(Instant agreementDate) {
		this.agreementDate = agreementDate;
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

	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Property )) return false;
	        return id != null && id.equals(((Property) o).id);
	    }
}
