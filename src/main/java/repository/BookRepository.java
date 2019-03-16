package repository;

import entity.BookEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<BookEntity, Integer>, JpaSpecificationExecutor<BookEntity> {

    @Query("Select b from BookEntity b where b.author.firstName =?1 and b.author.lastName=?2")
    List<BookEntity> getBookByAuthor(String firstName, String lastName);
}