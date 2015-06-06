package com.college.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.college.account.bean.MediaCategory;
import com.college.account.jersey.filter.Session;
import com.college.util.Cause;
import com.college.util.Json2Obj;
import com.college.util.Obj2Map;

public class DaoMediaCategoryService extends  DaoService<MediaCategory>
{
    public static int MEDIACATEGORYIDNOTFIND = 21000;
    public static int MEDIACATEGORYNAMENULL = 21001;
    public static int MEDIACATEGORYNAMEEXIST = 21002;

    public static String tablename = "MediaCategory";
    
    public boolean selIsExist(Integer id){      
        return (null != searchByid(id, tablename))?true:false;
    }
    
    private boolean selNameUniq(String name){
        
        return (null == searchByFeild(tablename, "name", name))?true:false;
    }

    public String save(String jsonString){

        MediaCategory mediacategory = (MediaCategory)Json2Obj.getObj(jsonString, MediaCategory.class);
        
        if(null == mediacategory.getName()){
            return Cause.getFailcode(MEDIACATEGORYNAMENULL, "name", "name can not null");
        }
        
        if(!selNameUniq(mediacategory.getName())){
            return Cause.getFailcode(MEDIACATEGORYNAMEEXIST, "name", "name is exist");
        }

        mediacategory.setCreateTime(new Date());
        mediacategory.setId(Session.getOperId());
        
        Integer id = create(mediacategory);
        
        return Cause.getSuccess(id);
    }
    
    
    public String sel(Integer id){
        
        MediaCategory mediacategory = searchByid(id, tablename);
        
        if(null == mediacategory){
            
            return Cause.getFailcode(MEDIACATEGORYIDNOTFIND, "id", "media category id not find");
        }
        
        List<Object> list = new ArrayList<Object>(1);
        
        Map<String, Object> map = Obj2Map.toMap(mediacategory, MediaCategory.class);
        
        list.add(map);
        
        return Cause.getData(list);
    }
    
    public String getAllObject(){
        
        List<Object> list = searchAll(tablename);
        return Cause.getData(list);
    }
    
    public String upd(Integer id, String jsonString){
        
        MediaCategory mediacategory = null;
        MediaCategory mediacategoryfind = null;
        
        mediacategory = (MediaCategory)Json2Obj.getObj(jsonString, MediaCategory.class);
        
        
        
        mediacategoryfind = searchByid(id, tablename);
        
        if(null == mediacategoryfind){
            return Cause.getFailcode(MEDIACATEGORYIDNOTFIND, "id", "media category id not find");
        }
        
        
        Json2Obj.updateObject(mediacategory, mediacategoryfind);
        
        update(mediacategoryfind);
        
        return Cause.getSuccess(id);
    }
    
    public String del(Integer id){
        
        MediaCategory mediacategory = null;
        
        mediacategory = searchByid(id, tablename);
        
        if(null == mediacategory){
            return Cause.getFailcode(MEDIACATEGORYIDNOTFIND, "id", "media category id not find");
        }
        
        delete(MediaCategory.class, id);
        
        return Cause.getSuccess(id);
    }
}
