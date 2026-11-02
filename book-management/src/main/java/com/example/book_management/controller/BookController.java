package com.example.book_management.controller;

import com.example.book_management.dto.BookCreateDTO;
import com.example.book_management.dto.BookUpdateStockDTO;
import com.example.book_management.entity.Book;
import com.example.book_management.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // --- API bài 1: Thêm mới sách ---
    @PostMapping
    public ResponseEntity<Book> createBook(@ModelAttribute BookCreateDTO dto) {
        Book savedBook = bookService.createBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    // --- API bài 2: Cập nhật tồn kho (Stock) ---
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateStockDTO dto) {

        bookService.updateBook(id, dto);
        return ResponseEntity.ok("Book stock updated successfully");

        // API 3: Lấy thông tin sách theo ID (Bài 3)
        @GetMapping("/{id}")
        public ResponseEntity<Book> getBookById(@PathVariable Long id) {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        }
    }
}