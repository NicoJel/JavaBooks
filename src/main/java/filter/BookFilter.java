package filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookFilter {
    private FilterWrapper<String> name;
    private FilterWrapper<String> author;
    private FilterWrapper<String> global;
    private FilterWrapper<Integer> year;
    private FilterWrapper<Integer> pages;

}
