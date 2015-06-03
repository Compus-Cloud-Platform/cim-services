package com.college.dao.basic;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.college.util.Logger4j;

/**
 * @author Dennis Dai
 */
public class DaoBasicService implements BasicDao
{
    private static final Logger log = Logger4j.getLogger(DaoBasicService.class);
    
    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    private HibernateTemplate getHibernateTemplate()
    {
        if(hibernateTemplate == null)
        {
            hibernateTemplate =  new HibernateTemplate(sessionFactory);
        }
        return hibernateTemplate;
    }
    
    /**
     * Creates an object in the database.
     * @param obj the object to create.
     * @return the created object.
     */
    public Serializable create(Object obj)
    {
        return getHibernateTemplate().save(obj);
    }

    /**
     * Creates objects in the database.
     * @param obj the objects to create.
     * @return the created objects.
     */
    public Object[] createAll(Object[] objs)
    {
        if (objs != null)
        {
            for (int n = 0; n < objs.length; n++)
                create(objs[n]);
        }
        return objs;
    }

    /**
     * Updates an object in the database.
     * @param obj the object to update.
     * @return the updated object.
     */
    public Object update(Object obj)
    {
        if (obj != null && log.isDebugEnabled())
        {
            log.debug("Want to update an object[className:" + obj.getClass().getName() + "; toString:" + obj.toString() + "]");
        }
        getHibernateTemplate().update(obj);
        return obj;
    }

    /**
     * Updates objects in the database.
     * @param obj the objects to update.
     * @return the updated objects.
     */
    public Object[] updateAll(Object[] objs)
    {
        if (objs != null)
        {
            for (int n = 0; n < objs.length; n++)
                update(objs[n]);
        }
        return objs;
    }

    /**
     * Deletes an object from the database.
     * @param obj the object to delete.
     */
    public void delete(Object obj)
    {
        if (obj != null)
            getHibernateTemplate().delete(obj);
    }

    /**
     * Deletes objects from the database.
     * @param obj the objects to delete.
     */
    public void deleteAll(Object[] objs)
    {
        if (objs != null)
        {
            for (int n = 0; n < objs.length; n++)
                create(objs[n]);
        }
    }
    
    /**
     * Delete an object from the database
     * @param clazz
     * @param id
     */
    public void delete(Class<?> clazz, int id)
    {
        Object obj = getHibernateTemplate().load(clazz, id);
        getHibernateTemplate().delete(obj);
    }
    
    /**
     * Finds an object from the database using the primary key.
     * @param clazz the object class.
     * @param id the primary key ID.
     * @return the object.
     */
    public Object get(@SuppressWarnings("rawtypes") Class clazz,int id)
    {
        return getHibernateTemplate().get(clazz, new Integer(id));
    }
    
    /**
     * Find all objects from database.
     * @param queryName is the query string.
     * @return List.
     */
    @SuppressWarnings("unchecked")
    public List<Object> query(String queryName)
    {
        return getHibernateTemplate().findByNamedQuery(queryName);
    }
    

    /**
     * 
     * @param queryName
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> query(String queryName, Object[] values)
    {
        return getHibernateTemplate().findByNamedQuery(queryName, values);
    }
    
    /**
     * 
     * @param queryName
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> query(final String queryName, final Integer from, final Integer size)
    {
    	/* 通过spring 获取语句  */
    	List<Object> list = getHibernateTemplate().executeFind(new  HibernateCallback(){

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query = session.getNamedQuery(queryName);
				
				query.setFirstResult(from);  
				  
	            query.setMaxResults(size); 
	  
	            List<?> list = query.list();
	  
	            return list; 
			}});  
    			  
    	return list; 
    }
    
    
    /**
     * 
     * @param queryName
     * @param values
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> query(final String queryName,final Object[] values, final Integer from, final Integer size)
    {
    	/* 通过spring 获取语句  */
    	List<Object> list = getHibernateTemplate().executeFind(new  HibernateCallback(){

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query query = session.getNamedQuery(queryName);
				
				for (int i = 0; i < values.length; i++)
				{
				    query.setParameter(i, values[i]);
				}
				
				query.setFirstResult(from);  
				  
	            query.setMaxResults(size);  
	  
	            List<?> list = query.list(); 
	  
	            return list; 
			}});  
    			  
    	return list; 
    }
    

    public Integer querycount(String queryName)
    {
        return Integer.parseInt(getHibernateTemplate().findByNamedQuery(queryName).get(0).toString());
    }
    

    public Integer querycount(String queryName, Object[] values)
    {
    	Object obj = getHibernateTemplate().findByNamedQuery(queryName, values).get(0);
        return Integer.parseInt(getHibernateTemplate().findByNamedQuery(queryName, values).get(0).toString());
    }
    
    
}
