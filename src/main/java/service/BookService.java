package service;


import assembly.BookAssembly;
import dto.BookDto;
import entity.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import repository.BookRepository;

import java.util.List;


@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto getBookById(int id) throws Exception {
        return BookAssembly.EntityToDto(bookRepository.findById(id).orElseThrow(() -> new Exception()));
    }

    public List<BookDto> getAllBooks() {
        return BookAssembly.EntitiesToDtos(bookRepository.findAll());
    }

    public List<BookDto> getAllBooks(Specification<BookEntity> specs, Pageable pageable) {
        return BookAssembly.EntitiesToDtos(bookRepository.findAll(specs, pageable));
    }

    public BookDto saveABook(BookDto bookDto) {
        return BookAssembly.EntityToDto(bookRepository.save(BookAssembly.DtoToEntity(bookDto)));
    }

    public void deleteABook(int id){
        bookRepository.deleteById(id);
    }

    public List<BookDto> getBookByAuthor(String firstName, String lastName) {
        return BookAssembly.EntitiesToDtos(bookRepository.getBookByAuthor(firstName, lastName));
    }
}
