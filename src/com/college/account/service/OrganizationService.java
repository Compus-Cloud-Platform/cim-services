package com.college.account.service;

import java.util.List;

import com.college.account.bean.Organization;

/**
 * @author Dennis Dai
 */
public interface OrganizationService
{
    /**
     * Create organization
     * @param organization is the Organization
     * @return Organization
     */
    public Integer createOrganization(Organization organization);
    
    /**
     * Update organization
     * @param organization is the Organization
     * @return Organization
     */
    public Organization updateOrganization(Organization organization);
    
    /**
     * Delete organization
     * @param organization is the Organization
     */
    public void deleteOrganization(Organization organization);
    
    
    /**
     * find all organization
     * @return List
     */
    public List findAllOrganizations();
    
    /**
     * find all organization.
     * @param value
     * @return List
     */
    public List findAllOrganizationsByName(String value);
    
    /**
     * 
     * @param clazz
     * @param id
     */
    public void deleteOrganization(Class clazz, Integer id);
    
    /**
     * 
     * @param value
     * @return
     */
    public Organization findAllOrganizationsById(Integer id);
    
    
}
