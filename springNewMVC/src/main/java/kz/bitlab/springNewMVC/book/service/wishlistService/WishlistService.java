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
        if(id!=null) {
            wishlistRepository.deleteById(id);
        } else {
            throw new RuntimeException("There is no Book with this ID");
        }
    }
    public Wishlist getById(Long id) {
        Optional<Wishlist> Wishlist = wishlistRepository.findById(id);
        if(Wishlist.isPresent()) {
            return Wishlist.get();
        } else {
            throw new RuntimeException("There is no book with this ID");
        }
    }
    public Wishlist create(CreateWishlist createWishlist) {

        Wishlist wishlist = new Wishlist();

        wishlist.setUserId(createWishlist.getUuid());

        wishlistRepository.save(wishlist);

        List<Book> books = new ArrayList<>();
        Book book = bookRepository.findAllById(createWishlist.getBook());
        books.add(book);

        wishlist.setBooks(books);
        
        return wishlistRepository.save(wishlist);
    }

    public Page<Wishlist> getAll(Pageable page) {
        Page<Wishlist> wishlist = wishlistRepository.findAll(page);
        return wishlist;
    }

}
