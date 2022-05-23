package com.alkemy.ong.model;



import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "slides")
@Getter
@Setter
public class Slides implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private String text;

}
