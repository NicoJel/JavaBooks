package controller;

import dto.AuthorDto;
import filter.AuthorFilter;
import filter.AuthorSpecificationBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AuthorService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="/author")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<AuthorDto>> allAuthors() throws IOException {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/allWSpecs")
    @ResponseBody
    public ResponseEntity<List<AuthorDto>> allAuthorsWithSpecs(AuthorFilter authorFilter, Pageable pageable) {
        AuthorSpecificationBuilder specBuilder = new AuthorSpecificationBuilder();
        return ResponseEntity.ok(authorService.getAllAuthors(specBuilder.buildClassicAuthorRequest(authorFilter), pageable));
    }
}
