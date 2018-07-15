package com.ezn.customer.portal.service.dto;

/**
 * A DTO representing a PropertyMetaData, with his authorities.
 */
public class PropertyMetaDataDTO {

    private int lotSize;

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

    public int getFloorSize() {
        return floorSize;
    }

    public void setFloorSize(int floorSize) {
        this.floorSize = floorSize;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    private int floorSize;
    private int floors;



    @Override
    public String toString() {
        return "PropertyMetaDataDTO{" +
            "lotSize='" + lotSize + '\'' +
            ", floorSize='" + floorSize + '\'' +
            ", floors='" + floors + '\'' +
            "}";
    }
}
