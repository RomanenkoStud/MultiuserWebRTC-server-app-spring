package com.example.demo.persistence.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_settings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class UserSettings {

    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "micro_on")
    private boolean isMicroOn;

    @Column(name = "camera_on")
    private boolean isCameraOn;

    @Column(name = "bloor_on")
    private boolean isBloorOn;
}
