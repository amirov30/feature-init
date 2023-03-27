package kz.bitlab.springNewMVC.book.util;

import kz.bitlab.springNewMVC.book.domain.dto.book.FilterParams;
import kz.bitlab.springNewMVC.book.domain.model.Book;
import kz.bitlab.springNewMVC.book.domain.model.Category;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BookSpecificationUtil {

    public static Function<FilterParams, Specification<Book>> withParams = params -> (root, query, criteriaBuilder) -> {
        List<Predicate> predicates = new ArrayList<>();

        if(params.getName() != null) {
            predicates.add(criteriaBuilder.equal(root.get("name"), params.getName()));
        }
        if(params.getDescription() != null) {
            predicates.add(criteriaBuilder.equal(root.get("description"), params.getDescription()));
        }
        if (params.getMinPrice() != null && params.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("price"), params.getMinPrice()),                      //Фильтр от МИН до МАХ,проходить по столбику с наз-ем PRICE и ищет
                    criteriaBuilder.lessThanOrEqualTo(root.get("price"), params.getMaxPrice())
            ));
        }
        else if (params.getMinPrice() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), params.getMinPrice()));

        } else if (params.getMaxPrice() != null) {
            predicates.add( criteriaBuilder.lessThanOrEqualTo(root.get("price"), params.getMaxPrice()));

        }
        if (params.getMinPages() != null && params.getMaxPages() != null) {
            predicates.add(criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("pages"), params.getMinPages()),
                    criteriaBuilder.lessThanOrEqualTo(root.get("pages"), params.getMaxPages())
            ));

        } else if (params.getMinPages() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("pages"), params.getMinPages()));


        } else if (params.getMaxPages() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("pages"), params.getMaxPages()));
        }
        if(params.getCategory() != null) {
            predicates.add(root.get("category").in(params.getCategory()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };

}
