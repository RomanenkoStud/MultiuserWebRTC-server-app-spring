package com.example.demo.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Pattern(regexp = ".+@.+\\..+")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;

    @Enumerated(EnumType.STRING)
    private Role role;
}
