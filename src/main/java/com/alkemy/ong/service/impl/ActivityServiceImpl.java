package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ActivityDTO;
import com.alkemy.ong.mapper.ActivityMapper;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public ActivityDTO save(ActivityDTO activityDTO) {
        Activity entity = activityMapper.activityDTOtoEntity(activityDTO);
        Activity activitySaved = activityRepository.save(entity);
        ActivityDTO dtoReturn = activityMapper.activityEntityToDTO(activitySaved);
        return dtoReturn;
    }

}
