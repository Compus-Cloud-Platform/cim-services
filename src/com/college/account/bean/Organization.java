package com.college.account.bean;

import java.util.Date;


public class Organization
{ 
	private Integer id;
    private String name;
    private String code;
    private String address;
    private String description;
    private String phone;
    private String fax;
    private String webSite;
    private Date createDate;
    private Integer operId;
    
   

	public Organization(){}
    
    public Organization(String name, String code, String address,
			String description, String phone, String fax, String webSite,
			Date createDate, Integer operId) {
		super();
		this.name = name;
		this.code = code;
		this.address = address;
		this.description = description;
		this.phone = phone;
		this.fax = fax;
		this.webSite = webSite;
		this.createDate = createDate;
		this.operId = operId;
	}
    
    public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getFax() {
		return fax;
	}



	public void setFax(String fax) {
		this.fax = fax;
	}



	public String getWebSite() {
		return webSite;
	}



	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Integer getOperId() {
		return operId;
	}
	 

	public void setOperId(Integer operId) {
		this.operId = operId;
	}

    @Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", code=" + code
				+ ", address=" + address + ", description=" + description
				+ ", phone=" + phone + ", fax=" + fax + ", webSite=" + webSite
				+ ", createDate=" + createDate + ", operId=" + operId + "]";
	}
}
