package com.alkemy.ong.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Table(name = "activities")
@SQLDelete(sql = "UPDATE activities SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class Activity implements Serializable{

    private static final long serialVersionUID = 1L;
    
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull(message = "Name cannot be empty")
    private String name;
	
	@NotNull(message = "Content cannot be empty")
    private Integer content;
    
	@NotNull(message = "Image cannot be empty")
    private String image;
    
    private Boolean deleted = Boolean.FALSE;
    
    @CreationTimestamp
    private LocalDateTime createDateTime;
    
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
}
