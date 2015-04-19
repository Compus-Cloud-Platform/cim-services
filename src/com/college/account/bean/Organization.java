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
    private Date updateDate;
    private int createBy;
    
    public Organization(){};
    
    

    public Organization(String name, String code, String address,
            String description, Date createDate, Date updateDate, int createBy)
    {
        super();
        this.name = name;
        this.code = code;
        this.address = address;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createBy = createBy;
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

    public Date getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public int getCreateBy()
    {
        return createBy;
    }

    public void setCreateBy(int createBy)
    {
        this.createBy = createBy;
    }

    @Override
    public String toString()
    {
        return "Organization [id=" + id + ", name=" + name + ", code=" + code
                + "]";
    }
}
