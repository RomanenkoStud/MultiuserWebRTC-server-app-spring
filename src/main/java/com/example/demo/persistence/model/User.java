package com.example.demo.persistence.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserSettings userSettings;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    public void addUserSettings(UserSettings userSettings) {
        userSettings.setUser(this);
        this.userSettings = userSettings;
    }

    public void addRoom(Room room) {
        room.setUser(this);
        rooms.add(room);
    }
}
