package com.ezn.customer.portal.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer
{
    @JsonProperty("Addresses")
    private Addresses[] Addresses;

    @JsonProperty("CustomId")
    private String CustomId;

    @JsonProperty("SourceTypeId")
    private String SourceTypeId = "11";

    @JsonProperty("QuickBooksNumber")
    private String QuickBooksNumber = "4445";

    @JsonProperty("CustomerTypeId")
    private String CustomerTypeId = "28";

    @JsonProperty("Contacts")
    private Contacts[] Contacts;

    @JsonProperty("QuickBooksDesktopListId")
    private String QuickBooksDesktopListId = "80000001-1390562257";

    @JsonProperty("CompanyName")
    private String CompanyName = "EZNesting";

   
    @JsonProperty("TierNumber")
    private String TierNumber = "1";

    @JsonProperty("DisplayName")
    private String DisplayName;

    public Addresses[] getAddresses ()
    {
        return Addresses;
    }

    public void setAddresses (Addresses[] Addresses)
    {
        this.Addresses = Addresses;
    }

    public String getCustomId ()
    {
        return CustomId;
    }

    public void setCustomId (String CustomId)
    {
        this.CustomId = CustomId;
    }

    public String getSourceTypeId ()
    {
        return SourceTypeId;
    }

    public void setSourceTypeId (String SourceTypeId)
    {
        this.SourceTypeId = SourceTypeId;
    }

    public String getQuickBooksNumber ()
    {
        return QuickBooksNumber;
    }

    public void setQuickBooksNumber (String QuickBooksNumber)
    {
        this.QuickBooksNumber = QuickBooksNumber;
    }

    public String getCustomerTypeId ()
    {
        return CustomerTypeId;
    }

    public void setCustomerTypeId (String CustomerTypeId)
    {
        this.CustomerTypeId = CustomerTypeId;
    }

    public Contacts[] getContacts ()
    {
        return Contacts;
    }

    public void setContacts (Contacts[] Contacts)
    {
        this.Contacts = Contacts;
    }

    public String getQuickBooksDesktopListId ()
    {
        return QuickBooksDesktopListId;
    }

    public void setQuickBooksDesktopListId (String QuickBooksDesktopListId)
    {
        this.QuickBooksDesktopListId = QuickBooksDesktopListId;
    }

    public String getCompanyName ()
    {
        return CompanyName;
    }

    public void setCompanyName (String CompanyName)
    {
        this.CompanyName = CompanyName;
    }

    public String getTierNumber ()
    {
        return TierNumber;
    }

    public void setTierNumber (String TierNumber)
    {
        this.TierNumber = TierNumber;
    }

    public String getDisplayName ()
    {
        return DisplayName;
    }

    public void setDisplayName (String DisplayName)
    {
        this.DisplayName = DisplayName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Addresses = "+Addresses+", CustomId = "+CustomId+", SourceTypeId = "+SourceTypeId+", QuickBooksNumber = "+QuickBooksNumber+", CustomerTypeId = "+CustomerTypeId+", Contacts = "+Contacts+", QuickBooksDesktopListId = "+QuickBooksDesktopListId+", CompanyName = "+CompanyName+", TierNumber = "+TierNumber+", DisplayName = "+DisplayName+"]";
    }
}