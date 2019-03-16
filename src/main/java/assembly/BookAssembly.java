package assembly;

import dto.BookDto;
import entity.BookEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookAssembly {

    public static BookDto EntityToDto(BookEntity bookEntity){
        BookDto result = BookDto.builder().id(1).name("test").build();
        result.setId(bookEntity.getId());
        result.setName(bookEntity.getName());
        result.setYear(bookEntity.getYear());
        result.setPages(bookEntity.getPages());
        result.setAuthor(AuthorAssembly.EntityToDto(bookEntity.getAuthor(), true));
        return result;
    }

    public static List<BookDto> EntitiesToDtos(Iterable<BookEntity> all) {
        return StreamSupport.stream(all.spliterator(), false).map(BookAssembly::EntityToDto).collect(Collectors.toList());
    }

    public static BookEntity DtoToEntity(BookDto bookDto){
        BookEntity result = new BookEntity();
        result.setId(bookDto.getId());
        result.setName(bookDto.getName());
        result.setAuthor(AuthorAssembly.DtoToEntity(bookDto.getAuthor()));
        result.setYear(bookDto.getYear());
        result.setPages(bookDto.getPages());
        return result;
    }

    public static List<BookEntity> DtosToEntities(List<BookDto> bookDtos){
        return bookDtos.stream().map(BookAssembly::DtoToEntity).collect(Collectors.toList());
    }
}
