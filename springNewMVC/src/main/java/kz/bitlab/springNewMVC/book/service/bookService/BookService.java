package kz.bitlab.springNewMVC.book.service.bookService;

import kz.bitlab.springNewMVC.book.domain.dto.book.CreateBookRequestDTO;
import kz.bitlab.springNewMVC.book.domain.dto.book.FilterParams;
import kz.bitlab.springNewMVC.book.domain.dto.book.UpdateBookRequestDTO;
import kz.bitlab.springNewMVC.book.domain.model.Book;
import kz.bitlab.springNewMVC.book.domain.model.Category;
import kz.bitlab.springNewMVC.book.repository.BookRepository;
import kz.bitlab.springNewMVC.book.util.BookSpecificationUtil;
import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class BookService implements BookServiceImpl {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void delete(Long id) {     //delete

        try {
            bookRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new RuntimeException ("There is no book with ID " + id);
        }
    }

    @Override
    public Book findById(Long id) {
        Optional<Book> maybeBook = bookRepository.findById(id);
        if(maybeBook.isPresent()) {
            return maybeBook.get();
        } else {
             throw new RuntimeException("There is no book with this ID");
        }
    }

    @Override
    public Book create(CreateBookRequestDTO dto) {
        Book book = new Book();

        book.setName(dto.getName());
        book.setDescription(dto.getDescription());
        book.setCategory(dto.getCategory());
        book.setPrice(dto.getPrice());
        book.setPages(dto.getPages());

        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, UpdateBookRequestDTO dto) {
        Optional<Book> maybeBook = bookRepository.findById(id);
        if(maybeBook.isPresent()) {            //Проверка на существование
            Book book = maybeBook.get();

             if (dto.getName() != null) {       //Проверка на отправленное имя //пришел ли запрос на обновление имени
                 book.setName(dto.getName());
             }
             if(dto.getDescription() != null) {
                 book.setDescription(dto.getDescription());
             }
             if (dto.getPrice() != null) {
                 book.setPrice(dto.getPrice());
             }
             if(dto.getPages() != null) {
                 book.setPages(dto.getPages());
             }
             return bookRepository.save(book);
        }
        throw new RuntimeException("Book with ID = [" + id + "] not found.");
    }


    public Page<Book> filter(String name, String description, Integer minPrice, Integer maxPrice, Integer minPages, Integer maxPages, Pageable pageable, List<Category> category) {
        FilterParams params = new FilterParams(
                name,
                description,
                minPrice,
                maxPrice,
                minPages,
                maxPages,
                category
        );
        return bookRepository.findAll(BookSpecificationUtil.withParams.apply(params), pageable);
    }
}
