package kz.bitlab.springNewMVC.book.controller;

import kz.bitlab.springNewMVC.book.domain.dto.category.CategoryRequest;
import kz.bitlab.springNewMVC.book.domain.dto.category.CreateCategory;
import kz.bitlab.springNewMVC.book.domain.model.Category;
import kz.bitlab.springNewMVC.book.repository.CategoryRepository;
import kz.bitlab.springNewMVC.book.service.categoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CreateCategory createCategory) {
        return ResponseEntity.ok(categoryService.create(createCategory));
    }
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id,
                           @RequestBody CategoryRequest categoryRequest) {
        return categoryService.update(id,categoryRequest);
    }
    @GetMapping
    public ResponseEntity<Page<Category>> getAll(@PageableDefault(size = 5) Pageable page) {
        return ResponseEntity.ok(categoryService.getAll(page));
    }
}
