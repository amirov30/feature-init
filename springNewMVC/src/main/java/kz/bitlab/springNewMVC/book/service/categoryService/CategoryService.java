package kz.bitlab.springNewMVC.book.service.categoryService;

import kz.bitlab.springNewMVC.book.domain.dto.category.CategoryRequest;
import kz.bitlab.springNewMVC.book.domain.dto.category.CreateCategory;
import kz.bitlab.springNewMVC.book.domain.model.Category;
import kz.bitlab.springNewMVC.book.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements CategoryServiceImpl {
    CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void delete(Long id) {     //delete
        if(id!=null) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("There is no category with this ID");
        }
    }
    @Override
    public Category findById(Long id) {
        if(id!=null) {
            return categoryRepository.findById(id).get();
        } else {
            throw new RuntimeException("There is no book with this ID");
        }
    }
    @Override
    public Category create(CreateCategory createCategory) {
        Category category = new Category();

        category.setName(createCategory.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Page<Category> getAll(Pageable page) {
        Page<Category>  categoryBook = categoryRepository.findAll(page);
        return categoryBook;
    }
//        @GetMapping
//    public ResponseEntity<Page<Book>> getAll(@PageableDefault(size = 5) Pageable page) {
//        Page<Book> booksPage = bookService.getAll(page);
//        return ResponseEntity.ok(booksPage);
//    }

    @Override
    public Category update(Long id, CategoryRequest categoryRequest) {
        Category category = null;
        if(categoryRepository.existsById(id)){            //Проверка на существование
            category = categoryRepository.findById(id).get();

            if (categoryRequest.getName() != null) {       //Проверка на отправленное имя //пришел ли запрос на обновление имени
                category.setName(categoryRequest.getName());
            }
            categoryRepository.save(category);
        }
        return category;
    }

}
