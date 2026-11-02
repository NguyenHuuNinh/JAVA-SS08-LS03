package com.example.book_management.dto;

import com.example.book_management.validation.ExistingBookId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BorrowCreateDTO {

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotNull(message = "Book ID cannot be null")
    @ExistingBookId(message = "Sách không tồn tại trong hệ thống")
    private Long bookId;
}