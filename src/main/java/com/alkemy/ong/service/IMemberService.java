package com.alkemy.ong.service;
import com.alkemy.ong.dto.MemberDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMemberService {

    @Transactional
    MemberDTO addMember(MemberDTO memberDto);


    MemberDto updateById(MemberDto dto, Long id);

    void deleteById(Long id);

    List<MemberDTO> getAll();
}
