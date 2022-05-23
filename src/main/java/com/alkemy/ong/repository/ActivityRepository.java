package com.alkemy.ong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alkemy.ong.model.Activity;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
    

}
