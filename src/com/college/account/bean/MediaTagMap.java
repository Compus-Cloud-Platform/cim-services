package com.college.account.bean;

public class MediaTagMap
{
    private Integer id;
    private Integer mediaId;
    private Integer mediaTagId;
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
        return mediaId;
    }
    public void setMediaId(Integer mediaId)
    {
        this.mediaId = mediaId;
    }
    public Integer getMediaTagId()
    {
        return mediaTagId;
    }
    public void setMediaTagId(Integer mediaTagId)
    {
        this.mediaTagId = mediaTagId;
    }
    public MediaTagMap(Integer mediaId, Integer mediaTagId)
    {
        super();
        this.mediaId = mediaId;
        this.mediaTagId = mediaTagId;
    }
    public MediaTagMap(){}
}
