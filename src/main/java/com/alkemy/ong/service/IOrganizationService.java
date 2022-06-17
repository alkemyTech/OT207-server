package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Organization;
import org.springframework.transaction.annotation.Transactional;

public interface IOrganizationService {

    @Transactional
    OrganizationDTO addOrganization(OrganizationDTO organizationDto);

    @Transactional(readOnly = true)
    OrganizationDTO getOrg();

    Organization updateOrganizationDto(OrganizationUpdateDTO orgUpdate) throws NotFoundException;

    void updateOrganization(Organization org);

}
