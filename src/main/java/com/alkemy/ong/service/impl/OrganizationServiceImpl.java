package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;


    @Override
    @Transactional
    public OrganizationDTO addOrganization(OrganizationDTO organizationDto) {

        Organization organization = organizationMapper.organizationDto2Entity(organizationDto);
        Organization savedEntity = organizationRepository.save(organization);
        return organizationMapper.organizationEntity2DTO(savedEntity);

    }

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
    public OrganizationUpdateDTO updateOrganizationDto(Long id, OrganizationUpdateDTO orgUpdate) throws NotFoundException {
        Optional<Organization> org = organizationRepository.findById(id);

        if (org.isEmpty()) {
            throw new NotFoundException("Organization id does not exist");
        }

        Organization savedOrg = this.organizationRepository.save(this.organizationMapper.organizationUpdateDTO2Entity(orgUpdate, org.get()));

            return organizationMapper.entity2organizationUpdateDTO(savedOrg);

    }
}