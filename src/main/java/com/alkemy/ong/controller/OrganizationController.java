package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.service.IOrganizationService;
import com.alkemy.ong.service.ISlideService;
import com.alkemy.ong.service.impl.SlideServiceImpl;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private ISlideService slidesService;

    @GetMapping("/public")
    public ResponseEntity<List<OrganizationDTO>> getOrganizationDTO(){
        List<OrganizationDTO> organizationDTOS = this.organizationService.findAll();
        return ResponseEntity.ok().body(organizationDTOS);
    }

    @GetMapping("/public/{organizationId}")
    public ResponseEntity<List<SlidesResponseDTO>> getSlidesByOrganizationId(@PathVariable Long organizationId){
        List<SlidesResponseDTO> slidesResponseDTOList = slidesService.getSlidesByOrganiztionId(organizationId);
        return ResponseEntity.ok().body(slidesResponseDTOList);
    }


    @PutMapping("/public/{id}")
    public ResponseEntity<OrganizationUpdateDTO> putUpdateOrganization (@PathVariable Long id, @RequestBody @Valid OrganizationUpdateDTO orgUpdate, BindingResult bindingResult){
    if (bindingResult.hasErrors()){
        throw new BadRequestException(bindingResult);
    }
        OrganizationUpdateDTO org = organizationService.updateOrganizationDto(id, orgUpdate);

    return ResponseEntity.ok().body(org);
}

    @PostMapping
    public ResponseEntity<OrganizationDTO> addOrganization(@Valid @RequestBody OrganizationDTO organizationDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        OrganizationDTO organizationDto = organizationService.addOrganization(organizationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationDto);
    }


}
