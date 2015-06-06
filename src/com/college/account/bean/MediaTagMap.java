package com.college.account.bean;

public class MediaTagMap
{
    private Integer id;
    private Integer MediaId;
    private Integer MediaTagId;
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getMediaId()
    {
        return MediaId;
    }
    public void setMediaId(Integer mediaId)
    {
        MediaId = mediaId;
    }
    public Integer getMediaTagId()
    {
        return MediaTagId;
    }
    public void setMediaTagId(Integer mediaTagId)
    {
        MediaTagId = mediaTagId;
    }
    public MediaTagMap(Integer mediaId, Integer mediaTagId)
    {
        super();
        MediaId = mediaId;
        MediaTagId = mediaTagId;
    }
    public MediaTagMap(){}
}
