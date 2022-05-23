package com.alkemy.ong.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@SQLDelete(sql = "UPDATE contacts SET deletedAt = true WHERE id=?")
@Where(clause = "deletedAt = false")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 75)
    @NotNull(message = "Name cannot be empty")
    private String name;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    @Column(nullable = false, unique = true)
    @Email(message = "Has to be an email format")
    @NotBlank
    private String email;

    @NotNull(message = "Name cannot be empty")
    private String message;

    private boolean deletedAt = Boolean.FALSE;
}
