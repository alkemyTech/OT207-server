package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDTO;
import java.util.List;

public interface IContactService {
    ContactDTO save(ContactDTO contactDTO);
    
    List<ContactDTO> getAll();
}
