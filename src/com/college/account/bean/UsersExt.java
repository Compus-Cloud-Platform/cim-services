package com.college.account.bean;

import java.util.Date;

public class UsersExt
{
    private int id;
    private String loginId;
    private String sex;
    private Date brithday;
    private String thumb;
    private String phone;
    private String address;
    
    public UsersExt(){}
            
    public UsersExt(String loginId, String sex, Date brithday, String thumb,
            String phone, String address)
    {
        super();
        this.loginId = loginId;
        this.sex = sex;
        this.brithday = brithday;
        this.thumb = thumb;
        this.phone = phone;
        this.address = address;
    }
    
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getLoginId()
    {
        return loginId;
    }
    public void setLoginId(String loginId)
    {
        this.loginId = loginId;
    }
    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    public Date getBrithday()
    {
        return brithday;
    }
    public void setBrithday(Date brithday)
    {
        this.brithday = brithday;
    }
    public String getThumb()
    {
        return thumb;
    }
    public void setThumb(String thumb)
    {
        this.thumb = thumb;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    @Override
    public String toString()
    {
        return "UsersExt [id=" + id + ", loginId=" + loginId + ", sex=" + sex
                + ", brithday=" + brithday + ", thumb=" + thumb + ", phone="
                + phone + ", address=" + address + "]";
    }
    

}
