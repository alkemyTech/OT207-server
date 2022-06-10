package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slides;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlidesRepository;
import com.alkemy.ong.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private SlidesRepository slidesRepository;

    @Autowired
    private SlideMapper slideMapper;


    @Transactional(readOnly = true)
    @Override
    public List<OrganizationDTO> findAll() {
        List<Organization> entities = organizationRepository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        List<OrganizationDTO> dtoList = organizationMapper.organizationEntityList2DTOList(entities);
        return dtoList;
    }

    @Override
    public Organization updateOrganizationDto(OrganizationUpdateDTO orgUpdate) throws NotFoundException {
        Optional<Organization> org = organizationRepository.findById(orgUpdate.getId());

        if (org.isEmpty()) {
            throw new NotFoundException("Organization id does not exist");
        } else {
            return organizationMapper.OrganizationUpdateDTO2Entity(orgUpdate, org.get());
        }
    }

    @Override
    public List<SlidesDTO> getSlidesByOrganization(Long id, String order) {
        Optional<Organization> entity = this.organizationRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Organization id does not exist");
        }

        List<Slides> slides = this.slidesRepository.findByOrganizations(entity);
        if (slides.isEmpty()) {
            throw new NotFoundException("The Slides list is empty");
        }

        if(order.equalsIgnoreCase("DESC")){
            slides.sort(Comparator.comparing(Slides :: getOrderSlides).reversed());
        }else{
            slides.sort(Comparator.comparing(Slides :: getOrderSlides));
        }
        return this.slideMapper.entitySlidesList2SlidesDtoList(slides);

    }

    @Override
    public void updateOrganization(Organization org) {
        organizationRepository.save(org);
    }

}
