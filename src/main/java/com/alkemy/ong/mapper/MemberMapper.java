package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member memberDtoToMemberEntity(MemberDto memberDto) {
        Member memberEntity = new Member();
        memberEntity.setId(memberDto.getId());
        memberEntity.setName(memberDto.getName());
        memberEntity.setFacebookUrl(memberDto.getFacebookUrl());
        memberEntity.setInstragramUrl(memberDto.getInstagramUrl());
        memberEntity.setLinkedinUrl(memberDto.getLinkedinUrl());
        memberEntity.setDescription(memberDto.getDescription());
        return memberEntity;
    }
    public MemberDto memberEntityToMemberDto(Member savedEntity) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(savedEntity.getId());
        memberDto.setName(savedEntity.getName());
        memberDto.setFacebookUrl(savedEntity.getFacebookUrl());
        memberDto.setInstagramUrl(savedEntity.getInstragramUrl());
        memberDto.setLinkedinUrl(savedEntity.getLinkedinUrl());
        memberDto.setDescription(savedEntity.getDescription());
        return memberDto;
    }
}
