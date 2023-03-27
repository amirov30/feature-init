package kz.bitlab.springNewMVC.book.controller;

import kz.bitlab.springNewMVC.book.domain.dto.book.CreateBookRequestDTO;
import kz.bitlab.springNewMVC.book.domain.dto.book.UpdateBookRequestDTO;
import kz.bitlab.springNewMVC.book.domain.model.Book;
import kz.bitlab.springNewMVC.book.domain.model.Category;
import kz.bitlab.springNewMVC.book.repository.BookRepository;
import kz.bitlab.springNewMVC.book.service.bookService.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody CreateBookRequestDTO dto) {
        return ResponseEntity.ok(bookService.create(dto));
    }

//    @GetMapping
//    public ResponseEntity<Page<Book>> getAll(@PageableDefault(size = 5) Pageable page) {
//        Page<Book> booksPage = bookService.getAll(page);
//        return ResponseEntity.ok(booksPage);
//    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id,
                       @RequestBody UpdateBookRequestDTO dto) {
         return bookService.update(id,dto);
    }


    @GetMapping
    public Page<Book> filter(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "minPrice", required = false) Integer minPrice,
            @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(name = "minPages", required = false) Integer minPages,
            @RequestParam(name = "maxPages", required = false) Integer maxPages,
            @RequestParam(name = "category", required = false) List<Category> category,
            Pageable pageable
    ) {
        return bookService.filter(name,description,minPrice,maxPrice,minPages,maxPages,pageable,category);

    }
}