package kz.bitlab.springNewMVC.book.domain.dto.wishlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWishlist {
    private UUID userId;
    private Long bookId;   //IdBook
}
