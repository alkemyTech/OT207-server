package com.alkemy.ong.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter

@Entity
@Table(name = "slides")
public class Slides implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private String text;

    private Integer orderSlides;// "order" genera un error en la sintaxis de la query para crear la tabla es una palabra reservada de sql

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organizations_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Organization organizations;
}
