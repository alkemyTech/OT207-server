package com.alkemy.ong.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name cannot be null")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotNull(message = "Password cannot be null")
    @Column(name = "password")
    private String password;

    @Nullable
    @Column(name = "photo")
    private String photo;

    @NotNull(message = "Rol cannot be null")
    @ManyToOne()
    @JoinColumn(name = "roles_id") 
    private Role roleId;
    
    private Boolean deleted = Boolean.FALSE;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
