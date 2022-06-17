package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizationMapper {

    private OrganizationRepository organizationRepository;

    public OrganizationDTO organizationEntity2DTO(Organization entity) {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setWelcomeText(entity.getWelcomeText());
        return dto;
    }

<<<<<<< HEAD
    public OrganizationUpdateDTO organizationEntityUpdateToDTO(Organization entity) {
        OrganizationUpdateDTO dto = new OrganizationUpdateDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setWelcomeText(entity.getWelcomeText());
        dto.setAboutUsText(entity.getAboutUsText());
        dto.setFacebookUrl(entity.getFacebookUrl());
        dto.setInstagramUrl(entity.getInstagramUrl());
        dto.setLinkedinUrl(entity.getLinkedinUrl());
        return dto;
    }

    public void organizationEntityRefreshValues(Organization entity, OrganizationUpdateDTO dto){
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setWelcomeText(dto.getWelcomeText());
        entity.setAboutUsText(dto.getAboutUsText());
        entity.setFacebookUrl(dto.getFacebookUrl());
        entity.setInstagramUrl(dto.getInstagramUrl());
        entity.setLinkedinUrl(dto.getLinkedinUrl());
        entity.setDeleted(false);
    }

    public List<OrganizationDTO> organizationEntityList2DTOList(@NotNull @NotEmpty List<Organization> entities) {
        List<OrganizationDTO> dtoList = new ArrayList<>();
        for (Organization entity : entities) {
            dtoList.add(this.organizationEntity2DTO(entity));
        }
        return dtoList;
    }

    public Organization OrganizationUpdateDTO2Entity(@NotNull OrganizationUpdateDTO orgUpdate, @NotNull Organization org){
=======
    public OrganizationDTO organizationEntity2DTOSlides(@NotNull Organization entity,  List<SlidesDTO> slidesDTOS) {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setWelcomeText(entity.getWelcomeText());
        dto.setSlidesDTOS(slidesDTOS);
        return dto;
    }

    public Organization organizationUpdateDTO2Entity(@NotNull OrganizationUpdateDTO orgUpdate, @NotNull Organization org){

>>>>>>> develop
        org.setName(orgUpdate.getName());
        org.setImage(orgUpdate.getImage());
        org.setAddress(orgUpdate.getAddress());
        org.setPhone(orgUpdate.getPhone());
        org.setEmail(orgUpdate.getEmail());
        org.setWelcomeText(orgUpdate.getWelcomeText());
        org.setAboutUsText(orgUpdate.getAboutUsText());
<<<<<<< HEAD
        return org;
=======

        return org;

    }

    public OrganizationUpdateDTO entity2organizationUpdateDTO(Organization org) {

        OrganizationUpdateDTO organizationUpdateDto = new OrganizationUpdateDTO();

        organizationUpdateDto.setId(org.getId());
        organizationUpdateDto.setName(org.getName());
        organizationUpdateDto.setImage(org.getImage());
        organizationUpdateDto.setAddress(org.getAddress());
        organizationUpdateDto.setPhone(org.getPhone());
        organizationUpdateDto.setEmail(org.getEmail());
        organizationUpdateDto.setWelcomeText(org.getWelcomeText());
        organizationUpdateDto.setAboutUsText(org.getAboutUsText());

        return organizationUpdateDto;

    }

    public Organization organizationDto2Entity(OrganizationDTO organizationDto) {
        Organization organization = new Organization();
        organization.setId(organizationDto.getId());
        organization.setName(organizationDto.getName());
        organization.setImage(organizationDto.getImage());
        organization.setPhone(organizationDto.getPhone());
        organization.setAddress(organizationDto.getAddress());
        organization.setEmail(organizationDto.getEmail());
        organization.setWelcomeText(organizationDto.getWelcomeText());
        return organization;
>>>>>>> develop
    }


}
