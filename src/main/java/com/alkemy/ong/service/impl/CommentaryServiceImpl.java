package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.auth.repository.UserRepository;
import com.alkemy.ong.auth.service.impl.UserDetailsCustomService;
import com.alkemy.ong.dto.CommentaryBodyDTO;
import com.alkemy.ong.dto.CommentaryDTO;
import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.exception.ForbiddenException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.UnauthorizedException;
import com.alkemy.ong.mapper.CommentaryMapper;
import com.alkemy.ong.model.*;
import com.alkemy.ong.repository.CommentaryRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.ICommentaryService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class CommentaryServiceImpl implements ICommentaryService {

    private static final String ID_NOT_FOUND = "Id not found: ";

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private NewsRepository newsRepository;

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

    @Override
    @Transactional
    public CommentaryBodyDTO update(Long id, CommentaryBodyDTO dto, HttpServletRequest request) {
        Optional<Commentary> entity = this.commentaryRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Commentary id does not exist");
        }
        Commentary commentary = commentaryRepository.findById(id).orElseThrow(() -> new NotFoundException(ID_NOT_FOUND + id));
        UserResponseDto responseDto = userDetailsCustomService.getProfile(request);
        UserEntity user = userRepository.findByEmail(responseDto.getEmail()).get();
        if (user.getId() == commentary.getUserEntity().getId() || request.isUserInRole("ROLE_ADMIN")) {
            this.mapper.entityCommentaryRefreshValues(entity.get(), dto);
            Commentary result = this.commentaryRepository.save(entity.get());
            return this.mapper.commentaryEntityBodyToDTO(result);
        } else {
            throw new ForbiddenException("User with no access.");
        }

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

    public List<CommentaryBodyDTO> findAllById(Long id){
        News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException("Post no exist Id: "+id));
        List<Commentary> listAll = commentaryRepository.findByNews(news);
        return mapper.entityListToDtoList(listAll);
    }

}
