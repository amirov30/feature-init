package kz.bitlab.springNewMVC.book.service.bookService;

import kz.bitlab.springNewMVC.book.domain.dto.book.CreateBookRequestDTO;
import kz.bitlab.springNewMVC.book.domain.dto.book.UpdateBookRequestDTO;
import kz.bitlab.springNewMVC.book.domain.model.Book;


import org.springframework.stereotype.Repository;
@Repository
public interface BookServiceImpl {
     void delete(Long id);
     Book findById(Long id);
     Book create(CreateBookRequestDTO dto);
     Book update (Long id, UpdateBookRequestDTO dto);
     //Page<Book> filterP(String name, String description, Integer minPrice, Integer maxPrice, Integer minPages, Integer maxPages, Pageable pageable);

     //Page<Book> filterP (String name, String description, Integer minPrice, Integer maxPrice, Integer minPages, Integer maxPages,Pageable pageable);
}
