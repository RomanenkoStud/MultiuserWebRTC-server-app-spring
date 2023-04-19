package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
