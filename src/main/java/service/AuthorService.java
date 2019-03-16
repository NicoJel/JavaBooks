package service;

import assembly.AuthorAssembly;
import dto.AuthorDto;
import entity.AuthorEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDto> getAllAuthors() {
        return AuthorAssembly.EntitiesToDtos(authorRepository.findAll());
    }

    public List<AuthorDto> getAllAuthors(Specification<AuthorEntity> specs, Pageable pageable) {
        return AuthorAssembly.EntitiesToDtos(authorRepository.findAll(specs, pageable));
    }
}
