package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.model.Organization;

import java.util.List;

public interface IOrganizationService {

    List<OrganizationDTO> findAll();
    
    Organization updateOrganizationDto(OrganizationUpdateDTO orgUpdate);
    
    void updateOrganization(Organization org);

}
