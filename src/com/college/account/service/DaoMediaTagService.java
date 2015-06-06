package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.MediaTag;
import com.college.account.jersey.filter.Session;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

public class DaoMediaTagService extends  DaoService<MediaTag>
{


    public static String tablename = "MediaTag";
   
    
    public static int MEDIATAGIDNOTFIND = 23000;
    public static int MEDIATAGNAMENULL = 23001;
    public static int MEDIATAGNAMEEXIST = 23002;

    
    public boolean selIsExist(Integer id){      
        return (null != searchByid(id, tablename))?true:false;
    }
    
    private boolean selNameUniq(String name){
        
        return (null == searchByFeild(tablename, "name", name))?true:false;
    }
    
    public String save(String jsonString){

        MediaTag mediatag = (MediaTag)Json2Obj.getObj(jsonString, MediaTag.class);
        
        if(null == mediatag.getName()){
            return Cause.getFailcode(MEDIATAGNAMENULL, "name", "name can not null");
        }
        
        if(!selNameUniq(mediatag.getName())){
            return Cause.getFailcode(MEDIATAGNAMEEXIST, "name", "name is exist");
        }

        mediatag.setCreateTime(new Date());
        mediatag.setId(Session.getOperId());
        
        Integer id = create(mediatag);
        
        return Cause.getSuccess(id);
    }
    
    
    public String sel(Integer id){
        
        MediaTag mediatag = searchByid(id, tablename);
        
        if(null == mediatag){
            
            return Cause.getFailcode(MEDIATAGIDNOTFIND, "id", "media tag id not find");
        }
        
        List<Object> list = new ArrayList<Object>(1);
        
        Map<String, Object> map = Obj2Map.toMap(mediatag, MediaTag.class);
        
        list.add(map);
        
        return Cause.getData(list);
    }
    
    public String getAllObject(){
        
        List<Object> list = searchAll(tablename);
        return Cause.getData(list);
    }
    
    public String upd(Integer id, String jsonString){
        
        MediaTag mediatag = null;
        MediaTag mediatagfind = null;
        
        mediatag = (MediaTag)Json2Obj.getObj(jsonString, MediaTag.class);
        
        
        
        mediatagfind = searchByid(id, tablename);
        
        if(null == mediatagfind){
            return Cause.getFailcode(MEDIATAGIDNOTFIND, "id", "media tag id not find");
        }
        
        
        Json2Obj.updateObject(mediatag, mediatagfind);
        
        update(mediatagfind);
        
        return Cause.getSuccess(id);
    }
    
    public String del(Integer id){
        
        MediaTag mediatag = null;
        
        mediatag = searchByid(id, tablename);
        
        if(null == mediatag){
            return Cause.getFailcode(MEDIATAGIDNOTFIND, "id", "media tag id not find");
        }
        
        delete(MediaTag.class, id);
        
        return Cause.getSuccess(id);
    }


}
