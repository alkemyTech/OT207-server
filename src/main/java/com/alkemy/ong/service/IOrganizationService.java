package com.alkemy.ong.service;

import com.alkemy.ong.dto.*;
import com.alkemy.ong.model.Organization;

import java.util.List;

public interface IOrganizationService {

    List<OrganizationDTO> findAll();
    
    Organization updateOrganizationDto(OrganizationUpdateDTO orgUpdate);
    
    void updateOrganization(Organization org);

    OrganizationUpdateDTO updateId (Long id , OrganizationUpdateDTO organizationDTO);


}
