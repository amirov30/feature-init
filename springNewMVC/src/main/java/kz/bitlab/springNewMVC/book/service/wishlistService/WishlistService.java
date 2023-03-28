package kz.bitlab.springNewMVC.book.service.wishlistService;

import kz.bitlab.springNewMVC.book.domain.dto.wishlist.CreateWishlist;
import kz.bitlab.springNewMVC.book.domain.model.Book;
import kz.bitlab.springNewMVC.book.domain.model.Wishlist;
import kz.bitlab.springNewMVC.book.repository.BookRepository;
import kz.bitlab.springNewMVC.book.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private BookRepository bookRepository;


    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }
    public void delete(Long id) {     //delete
        wishlistRepository.deleteById(id);
    }
    public Wishlist getById(Long id) {
        Optional<Wishlist> Wishlist = wishlistRepository.findById(id);
        if(Wishlist.isPresent()) {
            return Wishlist.get();
        } else {
            throw new RuntimeException("There is no book with this ID");
        }
    }
    public Wishlist addBook(CreateWishlist createWishlist) {
        Optional<Book> maybeBook = bookRepository.findById(createWishlist.getBookId());
        if(maybeBook.isEmpty()) {
            throw new RuntimeException();
        }
        Book book = maybeBook.get();
        Optional<Wishlist> maybeWish = wishlistRepository.findByUserId(createWishlist.getUserId());
        Wishlist wishlist;
        if(maybeWish.isPresent()){
            wishlist = maybeWish.get();
            wishlist.getBooks().add(book);
        } else {
            wishlist = new Wishlist();
            wishlist.setUserId(createWishlist.getUserId());

            List<Book> books = new ArrayList<>();
            books.add(book);

            wishlist.setBooks(books);
        }
        return wishlistRepository.save(wishlist);

    }

    public Page<Wishlist> getAll(Pageable page) {
        Page<Wishlist> wishlist = wishlistRepository.findAll(page);
        return wishlist;
    }

}
