package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;


    @Transactional(readOnly = true)
    @Override
    public List<OrganizationDTO> findAll() {
        List<Organization> entities = organizationRepository.findAll();
        List<OrganizationDTO> dtoList = organizationMapper.organizationEntityList2DTOList(entities);
        return dtoList;
    }
}
