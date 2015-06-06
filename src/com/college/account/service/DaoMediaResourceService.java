package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.MediaResource;
import com.college.account.jersey.filter.Session;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

public class DaoMediaResourceService extends  DaoService<MediaResource>
{
    public static int MEDIARESOURCEIDNOTFIND = 22000;
    public static int MEDIARESOURCENAMENULL = 22001;
    public static int MEDIARESOURCENAMEEXIST = 22002;

    public static String tablename = "MediaResource";
    
    public boolean selIsExist(Integer id){      
        return (null != searchByid(id, tablename))?true:false;
    }
    
    private boolean selNameUniq(String name){
        
        return (null == searchByFeild(tablename, "name", name))?true:false;
    }
    
    public String save(String jsonString){

        MediaResource mediaresource = (MediaResource)Json2Obj.getObj(jsonString, MediaResource.class);
        
        if(null == mediaresource.getName()){
            return Cause.getFailcode(MEDIARESOURCENAMENULL, "name", "name can not null");
        }
        
        if(!selNameUniq(mediaresource.getName())){
            return Cause.getFailcode(MEDIARESOURCENAMEEXIST, "name", "name is exist");
        }

        mediaresource.setCreateTime(new Date());
        mediaresource.setId(Session.getOperId());
        
        Integer id = create(mediaresource);
        
        return Cause.getSuccess(id);
    }
    
    
    public String sel(Integer id){
        
        MediaResource mediaresource = searchByid(id, tablename);
        
        if(null == mediaresource){
            
            return Cause.getFailcode(MEDIARESOURCEIDNOTFIND, "id", "media res id not find");
        }
        
        List<Object> list = new ArrayList<Object>(1);
        
        Map<String, Object> map = Obj2Map.toMap(mediaresource, MediaResource.class);
        
        list.add(map);
        
        return Cause.getData(list);
    }
    
    public String getAllObject(){
        
        List<Object> list = searchAll(tablename);
        return Cause.getData(list);
    }
    
    public String upd(Integer id, String jsonString){
        
        MediaResource mediaresource = null;
        MediaResource mediaresourcefind = null;
        
        mediaresource = (MediaResource)Json2Obj.getObj(jsonString, MediaResource.class);
        
        
        
        mediaresourcefind = searchByid(id, tablename);
        
        if(null == mediaresourcefind){
            return Cause.getFailcode(MEDIARESOURCEIDNOTFIND, "id", "media res id not find");
        }
        
        
        Json2Obj.updateObject(mediaresource, mediaresourcefind);
        
        update(mediaresourcefind);
        
        return Cause.getSuccess(id);
    }
    
    public String del(Integer id){
        
        MediaResource mediaresource = null;
        
        mediaresource = searchByid(id, tablename);
        
        if(null == mediaresource){
            return Cause.getFailcode(MEDIARESOURCEIDNOTFIND, "id", "media res id not find");
        }
        
        delete(MediaResource.class, id);
        
        return Cause.getSuccess(id);
    }

}
