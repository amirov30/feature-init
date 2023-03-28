package kz.bitlab.springNewMVC.book.repository;

import kz.bitlab.springNewMVC.book.domain.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
     Optional<Wishlist> findByUserId(UUID userId);
}
