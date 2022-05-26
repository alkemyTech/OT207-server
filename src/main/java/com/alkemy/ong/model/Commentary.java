package com.alkemy.ong.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@Table(name = "commentaries")
public class Commentary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "The body cannot be empty")
    private String body;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "news_id")
    private Long newsId;
}
