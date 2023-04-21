package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.RoomUserConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomUserConnectionRepository extends JpaRepository<RoomUserConnection, Long> {

    boolean existsByUserId(Long id);
}
