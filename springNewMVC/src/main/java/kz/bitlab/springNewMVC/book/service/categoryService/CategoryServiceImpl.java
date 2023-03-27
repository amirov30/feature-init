package kz.bitlab.springNewMVC.book.service.categoryService;

import kz.bitlab.springNewMVC.book.domain.dto.category.CategoryRequest;
import kz.bitlab.springNewMVC.book.domain.dto.category.CreateCategory;
import kz.bitlab.springNewMVC.book.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryServiceImpl {
    void delete(Long id);
    Category findById(Long id);
    Category create(CreateCategory createCategory);

    Page<Category> getAll(Pageable page);

    Category update (Long id, CategoryRequest categoryRequest);
//    List<Book> filter(String name, String description, Integer minPrice, Integer maxPrice, Integer minPages, Integer maxPages);
}
