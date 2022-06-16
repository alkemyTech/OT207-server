package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.auth.repository.UserRepository;
import com.alkemy.ong.auth.service.impl.UserDetailsCustomService;
import com.alkemy.ong.dto.CommentaryBodyDTO;
import com.alkemy.ong.dto.CommentaryDTO;
import com.alkemy.ong.exception.ForbiddenException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CommentaryMapper;
import com.alkemy.ong.model.Commentary;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.UserEntity;
import com.alkemy.ong.repository.CommentaryRepository;
import com.alkemy.ong.service.ICommentaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class CommentaryServiceImpl implements ICommentaryService {

    private static final String ID_NOT_FOUND = "Id not found: ";

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private CommentaryMapper mapper;

    @Override
    public CommentaryDTO save(CommentaryDTO commentaryDTO) {
        Commentary commentary = mapper.commentaryDTO2Entity(commentaryDTO);
        Commentary commentarySaved = commentaryRepository.save(commentary);
        return mapper.commentaryEntity2DTO(commentarySaved);
    }

    @Override
    public List<CommentaryBodyDTO> getCommentaries() {
        List<Commentary> entityList = commentaryRepository.findAllByOrderByCreationDate();
        if (entityList.isEmpty()) {
            throw new NotFoundException("Commentary list is empty");
        }
        List<CommentaryBodyDTO> dtoList = mapper.entityListToDtoList(entityList);
        return dtoList;
    }
    @Transactional
    @Override
    public void deleteById(Long id, HttpServletRequest request) {
        Commentary commentary = commentaryRepository.findById(id).orElseThrow(() -> new NotFoundException(ID_NOT_FOUND + id));
        UserResponseDto responseDto = userDetailsCustomService.getProfile(request);
        UserEntity user = userRepository.findByEmail(responseDto.getEmail()).get();
        if (user.getId() == commentary.getUserEntity().getId() || request.isUserInRole("ROLE_ADMIN")){
            commentaryRepository.deleteById(id);
        }else{
            throw new ForbiddenException("User with no access.");
        }
    }
}
