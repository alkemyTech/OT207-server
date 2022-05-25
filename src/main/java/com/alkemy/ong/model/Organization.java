package com.alkemy.ong.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "organizations")
@Getter
@Setter

@SQLDelete(sql = "UPDATE organizations SET softDelete = true WHERE id=?")
@Where(clause = "softDelete = false")

public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "Image cannot be empty")
    private String image;

    private String address;

    @Digits(integer = 10, fraction = 0)
    @Size(min = 9, max = 10)
    @NotEmpty
    private String phone;

    @Column(nullable = false)
    @NotEmpty(message = "email cannot be empty")
    @Email
    private String email;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty(message = "welcome text cannot be empty")
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
