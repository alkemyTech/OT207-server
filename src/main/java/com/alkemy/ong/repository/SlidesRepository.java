package com.alkemy.ong.repository;

import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slides;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlidesRepository extends JpaRepository<Slides, Long> {


    //@Query(nativeQuery = true, value = "SELECT s FROM Slides s WHERE organization.id = ? ORDER BY order_slides")

    List<Slides> findByOrganizations(Organization organization);

}