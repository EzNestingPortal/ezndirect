package com.ezn.customer.portal.service.dto; 

public class AvailableServiceDTO {
    private ServiceDTO service;
    private boolean available;

    public AvailableServiceDTO() {
    }

    public AvailableServiceDTO(ServiceDTO service, boolean available) {
        this.service = service;
        this.available = available;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }

    public ServiceDTO getService() {
        return service;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }
}