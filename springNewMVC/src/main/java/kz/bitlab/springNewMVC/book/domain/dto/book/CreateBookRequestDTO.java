package kz.bitlab.springNewMVC.book.domain.dto.book;

import kz.bitlab.springNewMVC.book.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequestDTO {
    private String name ;
    private String description ;
    private Category category;
    private int price ;
    private int pages ;

}
