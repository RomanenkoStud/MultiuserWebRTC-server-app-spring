package com.example.demo.persistence.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "room_user_connection")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class RoomUserConnection {

//    @EmbeddedId
//    private RoomUserConnectionId id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id"/*, insertable = false, updatable = false*/)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id"/*, insertable = false, updatable = false*/)
    private Room room;
}
