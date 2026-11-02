package com.example.book_management.controller;

import com.example.book_management.dto.BorrowCreateDTO;
import com.example.book_management.entity.Borrow;
import com.example.book_management.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public ResponseEntity<Borrow> createBorrow(@Valid @RequestBody BorrowCreateDTO dto) {
        Borrow savedBorrow = borrowService.createBorrow(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBorrow);
    }
}