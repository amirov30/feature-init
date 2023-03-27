package kz.bitlab.springNewMVC.book.domain.dto.book;

import kz.bitlab.springNewMVC.book.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FilterParams {
    String name;
    String description;
    Integer minPrice;
    Integer maxPrice;
    Integer minPages;
    Integer maxPages;
    List<Category> category;
}
