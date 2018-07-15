package com.ezn.customer.portal.service.dto;

import java.util.Date;
import java.util.List;

/**
 * A DTO representing a Plan, with his authorities.
 */
public class ServiceDTO {

    private String serviceId;
    private String serviceName;
    private String type;
    private Double invoiceAmount;
    private String serviceStatus;
    private Date scheduledDate;
    private Date completedDate;
    private String frequency;
    private Boolean recurring;
    private Boolean oneTime;
    private Boolean canRequestOneTime;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public Boolean getOneTime() {
        return oneTime;
    }

    public void setOneTime(Boolean oneTime) {
        this.oneTime = oneTime;
    }

    public Boolean getCanRequestOneTime() {
        return canRequestOneTime;
    }

    public void setCanRequestOneTime(Boolean canRequestOneTime) {
        this.canRequestOneTime = canRequestOneTime;
    }




    @Override
    public String toString() {
        return "PricingDTO{" +
            "serviceId='" + serviceId + '\'' +
            ", serviceName='" + serviceName + '\'' +
            ", type='" + type + '\'' +
            ", invoiceAmount='" + invoiceAmount + '\'' +
            ", serviceStatus='" + serviceStatus + '\'' +
            ", scheduledDate='" + scheduledDate + '\'' +
            ", completedDate=" + completedDate + '\'' +
            ", frequency=" + frequency + '\'' +
            ", recurring=" + recurring + '\'' +
            ", oneTime=" + oneTime + '\'' +
            ", canRequestOneTime=" + canRequestOneTime + '\'' +
            "}";
    }
}
