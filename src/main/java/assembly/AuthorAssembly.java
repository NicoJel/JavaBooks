package assembly;

import dto.AuthorDto;
import entity.AuthorEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AuthorAssembly {

    public static AuthorDto EntityToDto(AuthorEntity authorEntity, boolean eager){
        AuthorDto result = AuthorDto.builder().id(1).firstName("test").build();
        result.setId(authorEntity.getId());
        result.setFirstName(authorEntity.getFirstName());
        result.setLastName(authorEntity.getLastName());
        result.setNationality(authorEntity.getNationality());
        if(!eager){
            result.setBooks(BookAssembly.EntitiesToDtos(authorEntity.getBooks()));
        }
        return result;
    }

    public static AuthorDto EntityToDto(AuthorEntity authorEntity){
        return EntityToDto(authorEntity, false);
    }

    public static List<AuthorDto> EntitiesToDtos(Iterable<AuthorEntity> all) {
        return StreamSupport.stream(all.spliterator(), false).map(AuthorAssembly::EntityToDto).collect(Collectors.toList());
    }

    public static AuthorEntity DtoToEntity(AuthorDto authorDto){
        AuthorEntity result = new AuthorEntity();
        result.setId(authorDto.getId());
        result.setFirstName(authorDto.getFirstName());
        result.setLastName(authorDto.getLastName());
        result.setNationality(authorDto.getNationality());
        result.setBooks(BookAssembly.DtosToEntities(authorDto.getBooks()));
        return result;
    }

    public static List<AuthorEntity> DtosToEntities(List<AuthorDto> authorDtos){
        return authorDtos.stream().map(AuthorAssembly::DtoToEntity).collect(Collectors.toList());
    }
}
