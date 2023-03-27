package kz.bitlab.springNewMVC.book.domain.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequestDTO {
    private String name ;
    private String description ;
    private Integer price ;
    private Integer pages ;

}
