package com.college.dao.basic;

/**
 * @author Dennis Dai
 */
public abstract class AbstractServiceDao
{
    private BasicDao m_dao;

    public void setDao(BasicDao dao)
    {
        m_dao = dao;
    }

    public BasicDao getDao()
    {
        return m_dao;
    }
    
    
}
