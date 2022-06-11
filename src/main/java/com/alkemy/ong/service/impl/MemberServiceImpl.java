package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.exception.ConflictException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.mapper.MemberMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.MemberRepository;
import com.alkemy.ong.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public MemberDto addMember(MemberDto memberDto) {
        try {
            Member MemberEntity = memberMapper.memberDtoToMemberEntity(memberDto);
            Member savedEntity = memberRepository.save(MemberEntity);
            return memberMapper.memberEntityToMemberDto(savedEntity);
        } catch (Exception e) {
            throw new ConflictException("There is already a Member with this name " + memberDto.getName());
        }
    }
}
