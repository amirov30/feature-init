package kz.bitlab.springNewMVC.book.domain.dto.wishlist;

import kz.bitlab.springNewMVC.book.domain.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWishlist {
    private UUID uuid;
    private Book book;
}
