package com.ezn.customer.portal.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Addresses
{
    @JsonProperty("Description")
    private String Description;

    @JsonProperty("AddressLine1")
    private String AddressLine1;

    @JsonProperty("AddressTypeId")
    private String AddressTypeId = "31";

    @JsonProperty("AddressLine2")
    private String AddressLine2;

    public String getDescription ()
    {
        return Description;
    }

    public void setDescription (String Description)
    {
        this.Description = Description;
    }

    public String getAddressLine1 ()
    {
        return AddressLine1;
    }

    public void setAddressLine1 (String AddressLine1)
    {
        this.AddressLine1 = AddressLine1;
    }

    public String getAddressTypeId ()
    {
        return AddressTypeId;
    }

    public void setAddressTypeId (String AddressTypeId)
    {
        this.AddressTypeId = AddressTypeId;
    }

    public String getAddressLine2 ()
    {
        return AddressLine2;
    }

    public void setAddressLine2 (String AddressLine2)
    {
        this.AddressLine2 = AddressLine2;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Description = "+Description+", AddressLine1 = "+AddressLine1+", AddressTypeId = "+AddressTypeId+", AddressLine2 = "+AddressLine2+"]";
    }
}