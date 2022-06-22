package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.domain.model.Organization;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class OrganizationMapper {

    public OrganizationDTO organizationEntity2DTO(@NotNull Organization entity) {
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

        org.setName(orgUpdate.getName());
        org.setImage(orgUpdate.getImage());
        org.setAddress(orgUpdate.getAddress());
        org.setPhone(orgUpdate.getPhone());
        org.setEmail(orgUpdate.getEmail());
        org.setWelcomeText(orgUpdate.getWelcomeText());
        org.setAboutUsText(orgUpdate.getAboutUsText());

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
    }


}
