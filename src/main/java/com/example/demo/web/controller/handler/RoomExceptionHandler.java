package com.example.demo.web.controller.handler;

import com.example.demo.exception.RoomConnectionException;
import com.example.demo.exception.RoomNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoomExceptionHandler {

    @ExceptionHandler({RoomNotFoundException.class})
    public ResponseEntity<String> handleRoomNotFoundException(RoomNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler({RoomConnectionException.class})
    public ResponseEntity<String> handleRoomConnectionException(RoomConnectionException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
