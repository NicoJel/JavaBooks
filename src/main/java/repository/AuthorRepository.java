package repository;

import entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorRepository extends PagingAndSortingRepository<AuthorEntity, Integer>, JpaSpecificationExecutor<AuthorEntity> {

}
