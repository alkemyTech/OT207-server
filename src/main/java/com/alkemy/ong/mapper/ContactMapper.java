package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.model.Contact;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class ContactMapper {

    public Contact ContactDTO2Entity(@NotNull ContactDTO dto) {
        Contact entity = new Contact();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setMessage(dto.getMessage());
        return entity;
    }

    public ContactDTO ContactEntity2DTO(@NotNull Contact entity) {
        ContactDTO dto = new ContactDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setMessage(entity.getMessage());
        return dto;
    }
    
    public List<ContactDTO> entityListToDtoList(@NotNull List<Contact> listEntity){
        List<ContactDTO> listDto = new ArrayList();
        for (Contact entity : listEntity) {
            listDto.add(this.ContactEntity2DTO(entity));
        }
        return listDto;
    }
}

