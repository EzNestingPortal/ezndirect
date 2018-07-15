package com.ezn.customer.portal.service.dto;

import java.util.List;


/**
 * A DTO representing a subscription.
 */
public class SubscriptionDTO {

    private String subscriptionId;
    private String subscriptionName;
    private String frequency;
    private String promoCode;
    private Double yearlySavings;
    private String idleMonths;
    private AddressDTO address;
    private List<ServiceDTO> services;
    private Double discountedPrice;

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
    }

    private Double originalAmount;


    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public Double getYearlySavings() {
        return yearlySavings;
    }

    public void setYearlySavings(Double yearlySavings) {
        this.yearlySavings = yearlySavings;
    }

    public String getIdleMonths() {
        return idleMonths;
    }

    public void setIdleMonths(String idleMonths) {
        this.idleMonths = idleMonths;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

    public void setServices(List<ServiceDTO> services) {
        this.services = services;
    }



    @Override
    public String toString() {
        return "SubscriptionDTO{" +
            "subscriptionId='" + subscriptionId + '\'' +
            ", subscriptionName='" + subscriptionName + '\'' +
            ", frequency='" + frequency + '\'' +
            ", promoCode='" + promoCode + '\'' +
            ", yearlySavings='" + yearlySavings + '\'' +
            ", idleMonths=" + idleMonths +
            ", AddressDTO='" + address + '\'' +
            ", ServiceDTO=" + services +
            "}";
    }
}
