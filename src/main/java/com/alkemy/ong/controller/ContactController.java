package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.service.IContactService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    
    @Autowired
    private IContactService contactService;
    
    @GetMapping
    public ResponseEntity<List<ContactDTO>> getContacts () {
        List<ContactDTO> listDto = this.contactService.getAll();
        return ResponseEntity.ok().body(listDto);
    }
}
