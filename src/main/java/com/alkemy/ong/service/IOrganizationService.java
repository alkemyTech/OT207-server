package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slides;

import java.util.List;

public interface IOrganizationService {

    List<OrganizationDTO> findAll();
    
    Organization updateOrganizationDto(OrganizationUpdateDTO orgUpdate);

    List<SlidesDTO> getSlidesByOrganization(Long id, String order);

    void updateOrganization(Organization org);

}
