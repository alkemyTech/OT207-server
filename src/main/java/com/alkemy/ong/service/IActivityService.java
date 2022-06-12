package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityDTO;

public interface IActivityService {

    ActivityDTO save(ActivityDTO activityDTO);

    ActivityDTO update(Long id, ActivityDTO activityDTO);
}
