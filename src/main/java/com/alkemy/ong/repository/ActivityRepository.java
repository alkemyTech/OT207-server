package com.alkemy.ong.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.alkemy.ong.model.Activity;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
    
    List<Activity> findByMoviesId( Long idActivity);
       
     @Query ("SELECT c FROM Activity a WHERE a.name LIKE CONCAT('%', :dato,'%') ")
    List<Activity>findByName(@Param("dato")String name );

}
