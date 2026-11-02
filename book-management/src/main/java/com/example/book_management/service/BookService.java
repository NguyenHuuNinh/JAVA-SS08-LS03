package com.example.book_management.service;

import com.example.book_management.dto.BookCreateDTO;
import com.example.book_management.dto.BookUpdateStockDTO;
import com.example.book_management.entity.Book;
import com.example.book_management.exception.ResourceNotFoundException;
import com.example.book_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private final String UPLOAD_DIR = "src/main/resources/uploads/";

    // --- Tính năng bài 1: Thêm mới sách ---
    public Book createBook(BookCreateDTO dto) {
        String coverUrl = null;

        if (dto.getCoverImage() != null && !dto.getCoverImage().isEmpty()) {
            MultipartFile file = dto.getCoverImage();
            try {
                File dir = new File(UPLOAD_DIR);
                if (!dir.exists()) dir.mkdirs();

                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                coverUrl = filePath.toString().replace("/", "\\");
            } catch (IOException e) {
                throw new RuntimeException("Lỗi lưu file ảnh");
            }
        }

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setStock(dto.getStock());
        book.setCoverUrl(coverUrl);

        return bookRepository.save(book);
    }

    // --- Tính năng bài 2: Cập nhật tồn kho (Stock) ---
    public void updateBook(Long id, BookUpdateStockDTO dto) {
        // Tìm sách theo ID. Nếu không thấy -> ném ResourceNotFoundException
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));

        // Nếu thấy -> set lại thông tin -> save()
        book.setStock(dto.getStock());
        bookRepository.save(book);

        // 3. Tìm kiếm chi tiết sách theo ID (Bài 3)
        public Book getBookById(Long id) {
            return bookRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        }
    }
}