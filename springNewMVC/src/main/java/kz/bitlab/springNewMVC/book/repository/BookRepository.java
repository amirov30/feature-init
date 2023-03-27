package kz.bitlab.springNewMVC.book.repository;

import kz.bitlab.springNewMVC.book.domain.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository <Book,Long>, JpaSpecificationExecutor<Book> {
    Book findAllById(Book book);
}
