package com.alkemy.ong.service;
import com.alkemy.ong.dto.MemberDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMemberService {

    @Transactional
    MemberDTO addMember(MemberDTO memberDto);


    MemberDTO updateById(MemberDTO dto, Long id);

    void deleteById(Long id);

    List<MemberDTO> getAll();
}
