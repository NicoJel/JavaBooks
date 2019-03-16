package controller;

import dto.BookDto;
import filter.BookFilter;
import filter.BookSpecificationBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BookService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<BookDto> findABook(@PathVariable("id") int id) {
        try{
            return ResponseEntity.ok(bookService.getBookById(id));
        } catch (Exception e) {
            return new ResponseEntity<BookDto>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<BookDto>> allBooks() throws IOException {
        //throw new IOException();
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/allWSpecs")
    @ResponseBody
    public ResponseEntity<List<BookDto>> allBooksWithSpecs(BookFilter bookFilter, Pageable pageable) {
        BookSpecificationBuilder specBuilder = new BookSpecificationBuilder();
        return ResponseEntity.ok(bookService.getAllBooks(specBuilder.buildClassicBookRequest(bookFilter), pageable));
    }

    @GetMapping("/allWSpecsOr")
    @ResponseBody
    public ResponseEntity<List<BookDto>> buildOrBookRequest(BookFilter bookFilter, Pageable pageable) {
        BookSpecificationBuilder specBuilder = new BookSpecificationBuilder();
        return ResponseEntity.ok(bookService.getAllBooks(specBuilder.buildOrBookRequest(bookFilter), pageable));
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<BookDto> allBooks(@RequestBody BookDto book) {
        try {
            return new ResponseEntity<>(bookService.saveABook(book), HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR
                    ).build();
        }
    }

    @PostMapping("/{id}/delete")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") int id) {
        try {
            bookService.deleteABook(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<BookDto>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byauthor/{firstName}&{lastName}")
    @ResponseBody
    public ResponseEntity<List<BookDto>> author(@PathVariable("firstName") String firstName, @PathVariable("firstName") String lastName) {
        return ResponseEntity.ok(bookService.getBookByAuthor(firstName, lastName));
    }


//    @GetMapping("/search")
//    @ResponseBody
//    public List<BookDto> search(@RequestParam(value = "search") String search) {
//        BookSpecificationsBuilder builder = new BookSpecificationsBuilder();
//        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
//        Matcher matcher = pattern.matcher(search + ",");
//        while (matcher.find()) {
//            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
//        }
//
//        BookSpecificationBuilder spec = builder.build();
//        return BookRepository.findAll(spec);
//    }

}
