package com.college.account.bean;

import java.util.Date;

public class MediaResource
{
    private Integer id;
    private String name;
    private String url;
    private String type;
    private Integer cid;
    private String description;
    private Date createTime;
    private Integer operId;
    
    public MediaResource(){}
    
    public MediaResource(String name, String url, String type, Integer cid,
            String description, Date createTime, Integer operId)
    {
        super();
        this.name = name;
        this.url = url;
        this.type = type;
        this.cid = cid;
        this.description = description;
        this.createTime = createTime;
        this.operId = operId;
    }
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
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
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public Integer getCid()
    {
        return cid;
    }
    public void setCid(Integer cid)
    {
        this.cid = cid;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public Date getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    public Integer getOperId()
    {
        return operId;
    }
    public void setOperId(Integer operId)
    {
        this.operId = operId;
    }
    
    

}
