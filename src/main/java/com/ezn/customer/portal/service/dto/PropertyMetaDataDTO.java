package com.ezn.customer.portal.service.dto;

/**
 * A DTO representing a PropertyMetaData, with his authorities.
 */
public class PropertyMetaDataDTO {

    private int lawnSize;
    private int propertySize;
    private int floors;

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




    @Override
    public String toString() {
        return "PropertyMetaDataDTO{" +
            "lotSize='" + lawnSize + '\'' +
            ", floorSize='" + propertySize + '\'' +
            ", floors='" + floors + '\'' +
            "}";
    }
}
