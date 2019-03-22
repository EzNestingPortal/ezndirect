package com.ezn.customer.portal.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contacts
{
    @JsonProperty("Phone")
    private String Phone = "";

    @JsonProperty("ContactTypeId")
    private String ContactTypeId = "21";

    @JsonProperty("Email")
    private String Email;

    @JsonProperty("NotifyViaSms")
    private String NotifyViaSms = "true";

    @JsonProperty("Fax")
    private String Fax = "";

    @JsonProperty("CarrierId")
    private String CarrierId ="5";

    @JsonProperty("FirstName")
    private String FirstName;

    @JsonProperty("LastName")
    private String LastName;

    @JsonProperty("MobilePhone")
    private String MobilePhone;

    @JsonProperty("NotifyViaEmail")
    private String NotifyViaEmail = "true";

    
    public String getPhone ()
    {
        return Phone;
    }

    public void setPhone (String Phone)
    {
        this.Phone = Phone;
    }

    public String getContactTypeId ()
    {
        return ContactTypeId;
    }

    public void setContactTypeId (String ContactTypeId)
    {
        this.ContactTypeId = ContactTypeId;
    }

    public String getEmail ()
    {
        return Email;
    }

    public void setEmail (String Email)
    {
        this.Email = Email;
    }

    public String getNotifyViaSms ()
    {
        return NotifyViaSms;
    }

    public void setNotifyViaSms (String NotifyViaSms)
    {
        this.NotifyViaSms = NotifyViaSms;
    }

    public String getFax ()
    {
        return Fax;
    }

    public void setFax (String Fax)
    {
        this.Fax = Fax;
    }

    public String getCarrierId ()
    {
        return CarrierId;
    }

    public void setCarrierId (String CarrierId)
    {
        this.CarrierId = CarrierId;
    }

    public String getFirstName ()
    {
        return FirstName;
    }

    public void setFirstName (String FirstName)
    {
        this.FirstName = FirstName;
    }

    public String getLastName ()
    {
        return LastName;
    }

    public void setLastName (String LastName)
    {
        this.LastName = LastName;
    }

    public String getMobilePhone ()
    {
        return MobilePhone;
    }

    public void setMobilePhone (String MobilePhone)
    {
        this.MobilePhone = MobilePhone;
        this.Fax = MobilePhone;
        this.Phone = MobilePhone;
    }

    public String getNotifyViaEmail ()
    {
        return NotifyViaEmail;
    }

    public void setNotifyViaEmail (String NotifyViaEmail)
    {
        this.NotifyViaEmail = NotifyViaEmail;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Phone = "+Phone+", ContactTypeId = "+ContactTypeId+", Email = "+Email+", NotifyViaSms = "+NotifyViaSms+", Fax = "+Fax+", CarrierId = "+CarrierId+", FirstName = "+FirstName+", LastName = "+LastName+", MobilePhone = "+MobilePhone+", NotifyViaEmail = "+NotifyViaEmail+"]";
    }
}