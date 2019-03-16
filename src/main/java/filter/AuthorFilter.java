package filter;

import dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorFilter {
    private FilterWrapper<String> firstName;
    private FilterWrapper<String> lastName;
    private FilterWrapper<String> nationality;
    private FilterWrapper<List<BookDto>> books;

}
