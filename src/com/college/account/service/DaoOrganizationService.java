package com.college.account.service;


import com.college.account.bean.Organization;
import com.college.dao.basic.AbstractServiceDao;

/**
 * @author Dennis Dai
 */
public class DaoOrganizationService extends  DaoService<Organization>
{


	


	

//    /**
//     * Create organization
//     * @param organization is the Organization
//     * @return Organization
//     */
//    public Integer createOrganization(Organization organization)
//    {
//        return (Integer) this.getDao().create(organization);
//    }
//
//    /**
//     * Update organization
//     * @param organization is the Organization
//     * @return Organization
//     */
//    public Organization updateOrganization(Organization organization)
//    {
//        return (Organization) this.getDao().update(organization);
//    }
//
//    /**
//     * Delete organization
//     * @param organization is the Organization
//     */
//    public void deleteOrganization(Organization organization)
//    {
//        this.getDao().delete(organization);
//    }
//    
//    
//    /**
//     * find all organization
//     * @return List
//     */
//    public List findAllOrganizations()
//    {
//        return getDao().query("getAllOrganization");
//    }
//    
//    /**
//     * find all organization.
//     * @param value
//     * @return List
//     */
//    public List findAllOrganizationsByName(String value)
//    {
//        return getDao().query("getAllOrganizationByName", new String[]{value});
//    }
//
//	@Override
//	public void deleteOrganization(Class clazz, Integer id) {
//		// TODO Auto-generated method stub
//		this.getDao().delete(clazz, id.intValue());
//		
//	}
}
