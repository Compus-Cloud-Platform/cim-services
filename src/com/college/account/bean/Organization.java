package com.college.account.bean;

import java.util.Date;


public class Organization
{
    private int id;
    private String name;
    private String code;
    private String address;
    private String description;
    private Date createDate;
    private String phone;
    private String fax;
    private String webSite;
    private int operId;

    public Organization()
    {
    };

    public Organization(String name, String code, String address,
            String description, Date createDate, String phone, String fax,
            String webSite, int operId)
    {
        super();
        this.name = name;
        this.code = code;
        this.address = address;
        this.description = description;
        this.createDate = createDate;
        this.phone = phone;
        this.fax = fax;
        this.webSite = webSite;
        this.operId = operId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getWebSite()
    {
        return webSite;
    }

    public void setWebSite(String webSite)
    {
        this.webSite = webSite;
    }

    public int getOperId()
    {
        return operId;
    }

    public void setOperId(int operId)
    {
        this.operId = operId;
    }

    @Override
    public String toString()
    {
        return "Organization [id=" + id + ", name=" + name + ", code=" + code
                + "]";
    }
}
