package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDTO;
import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private IOrganizationService organization;

    @GetMapping("/public")
    public ResponseEntity<List<OrganizationDTO>> getOrganizationDTO(){
        List<OrganizationDTO> organizationDTOS = this.organization.findAll();
        return ResponseEntity.ok().body(organizationDTOS);
    }
    
    
    @PostMapping("/public")
    public ResponseEntity<OrganizationUpdateDTO> putUpdateOrganization (@RequestBody @Valid OrganizationUpdateDTO orgUpdate, BindingResult bindingResult){
    if (bindingResult.hasErrors()){
        throw new BadRequestException(bindingResult);
    }
    Organization org = organization.updateOrganizationDto(orgUpdate);
    organization.updateOrganization(org);
    return ResponseEntity.ok().body(orgUpdate);
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationUpdateDTO> updateOrganizationById(@PathVariable("id") Long id,
                                                                          @Valid @RequestBody OrganizationUpdateDTO organizationDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult);
        }
        OrganizationUpdateDTO resultDTO = organization.updateId(id, organizationDTO);
        return ResponseEntity.ok().body(resultDTO);
    }

}
