package com.college.dao.basic;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dennis Dai
 */
public interface BasicDao
{
    /**
     * Creates an object in the database.
     * @param obj the object to create.
     * @return the created object.
     */
    public Serializable create(Object obj);

    /**
     * Creates objects in the database.
     * @param obj the objects to create.
     * @return the created objects.
     */
    public Object[] createAll(Object[] obj);

    /**
     * Updates an object in the database.
     * @param obj the object to update.
     * @return the updated object.
     */
    public Object update(Object obj);

    /**
     * Updates objects in the database.
     * @param obj the objects to update.
     * @return the updated objects.
     */
    public Object[] updateAll(Object[] obj);

    /**
     * Deletes an object from the database.
     * @param obj the object to delete.
     */
    public void delete(Object obj);

    /**
     * Deletes objects from the database.
     * @param obj the objects to delete.
     */
    public void deleteAll(Object[] obj);
    
    /**
     * Delete an object from the database
     * @param clazz
     * @param id
     */
    public void delete(Class clazz, Integer id);

    /**
     * Finds an object from the database using the primary key.
     * @param clazz the object class.
     * @param id the primary key ID.
     * @return the object.
     */
    public Object get(@SuppressWarnings("rawtypes") Class clazz,Integer id);
    
    /**
     * Find all objects from database.
     * @param queryName is the query string.
     * @return List.
     */
    public List<Object> query(String queryName);
    
    
    /**
     * 
     * @param queryName
     * @param values
     * @return
     */
    public List<Object> query(String queryName, Object[] values);
}
