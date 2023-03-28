package kz.bitlab.springNewMVC.book.controller;

import kz.bitlab.springNewMVC.book.domain.dto.wishlist.CreateWishlist;
import kz.bitlab.springNewMVC.book.domain.model.Wishlist;
import kz.bitlab.springNewMVC.book.repository.WishlistRepository;
import kz.bitlab.springNewMVC.book.service.wishlistService.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/list")
public class WishlistController {
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private WishlistService wishlistService;

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        wishlistService.delete(id);
    }

    @PutMapping("/add")
    public ResponseEntity<Wishlist> create(@RequestBody CreateWishlist createWishlist) {
        return ResponseEntity.ok(wishlistService.addBook(createWishlist));
    }
    @GetMapping
    public ResponseEntity<Page<Wishlist>> getAll(@PageableDefault(size = 5) Pageable page) {
        return ResponseEntity.ok(wishlistService.getAll(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getById(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.getById(id));
    }
}
