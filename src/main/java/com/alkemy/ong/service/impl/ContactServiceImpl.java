package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.IContactService;
import com.alkemy.ong.mapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper mapper;

    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        Contact entity = mapper.ContactDTO2Entity(contactDTO);
        Contact entitySaved = contactRepository.save(entity);
        ContactDTO dtoReturn = mapper.ContactEntity2DTO(entitySaved);
        return dtoReturn;
    }
}
