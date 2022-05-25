package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private IOrganizationService organization;

    @GetMapping("/public")
    ResponseEntity<List<OrganizationDTO>> getOrganizationDTO(){
        List<OrganizationDTO> organizationDTOS = this.organization.findAll();
        return ResponseEntity.ok().body(organizationDTOS);
    }


}
