package com.example.book_management.validation;

import com.example.book_management.repository.BookRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookIdValidator implements ConstraintValidator<ExistingBookId, Long> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean isValid(Long bookId, ConstraintValidatorContext context) {
        if (bookId == null) {
            return false;
        }
        // Kiểm tra sách có tồn tại trong DB không
        return bookRepository.existsById(bookId);
    }
}