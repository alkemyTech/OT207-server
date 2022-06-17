package com.alkemy.ong.service;

<<<<<<< HEAD
import com.alkemy.ong.dto.*;
=======
import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.exception.NotFoundException;
>>>>>>> develop
import com.alkemy.ong.model.Organization;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrganizationService {

    @Transactional
    OrganizationDTO addOrganization(OrganizationDTO organizationDto);

    @Transactional(readOnly = true)
    OrganizationDTO getOrg();


<<<<<<< HEAD
    OrganizationUpdateDTO updateId (Long id , OrganizationUpdateDTO organizationDTO);


=======
    Organization updateOrganizationDto(OrganizationUpdateDTO orgUpdate) throws NotFoundException;

    void updateOrganization(Organization org);
>>>>>>> develop
}
