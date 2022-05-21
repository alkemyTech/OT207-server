package com.alkemy.ong.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

// COMO desarrollador
//        QUIERO agregar la entidad Organization
//        PARA representar en la implementación la estructura de datos
//
//        Criterios de aceptación:
//        Nombre de tabla: organizations. Los campos son:
//        name: VARCHAR NOT NULL
//        image: VARCHAR NOT NULL
//        address: VARCHAR NULLABLE
//        phone: INTEGER NULLABLE
//        email: VARCHAR NOT NULL
//        welcomeText: TEXT NOT NULL
//        aboutUsText: TEXT NULLABLE
//        timestamps y softDelete

@Entity
@Table(name = "organization")
@Getter
@Setter

@SQLDelete(sql = "UPDATE organization SET softDelete = true WHERE id=?")
@Where(clause = "softDelete = false")

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    private String address;

    private Integer phone;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "text", nullable = false)
    private String welcomeText;

    @Column(columnDefinition = "text")
    private String aboutUsText;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private boolean softDelete = Boolean.FALSE;
}
