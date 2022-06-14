package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.model.Organization;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrganizationService {

    @Transactional
    OrganizationDTO addOrganization(OrganizationDTO organizationDto);

    List<OrganizationDTO> findAll();
    
    Organization updateOrganizationDto(OrganizationUpdateDTO orgUpdate);
    
    void updateOrganization(Organization org);

}
