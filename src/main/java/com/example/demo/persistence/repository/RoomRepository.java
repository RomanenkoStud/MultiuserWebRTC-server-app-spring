package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.Room;
import com.example.demo.persistence.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Page<Room> findAllByUserId(Long id, Pageable pageable);
}
