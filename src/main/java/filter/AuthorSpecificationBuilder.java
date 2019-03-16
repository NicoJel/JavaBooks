package filter;

import entity.AuthorEntity;
import entity.AuthorEntity_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class AuthorSpecificationBuilder {

    public Specification<AuthorEntity> buildClassicAuthorRequest(AuthorFilter authorFilter) {
        return Specification.where(withFirstNameOperator(authorFilter.getFirstName()));
    }

    private Specification<AuthorEntity> withFirstNameOperator(FilterWrapper<String> name) {
        if (name == null)
            return null;
        return new Specification<AuthorEntity>() {
            @Override
            public Predicate toPredicate(Root<AuthorEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return getPredicate(root, root.get(AuthorEntity_.firstName), criteriaBuilder, name);
            }
        };
    }
        private Predicate getPredicate(Root<AuthorEntity> root, Path path, CriteriaBuilder criteriaBuilder, FilterWrapper wrapper) {
            switch (wrapper.getOperator()) {
                case CONTAINS:
                    return criteriaBuilder.like(path, "%" + wrapper.getValue() + "%");
                case EQUALS:
                    return criteriaBuilder.equal(path, wrapper.getValue());
                case LOWERTHAN:
                    if (wrapper.getValue() instanceof Integer) {
                        return criteriaBuilder.lessThan(path, (Integer)wrapper.getValue());
                    }
                    return criteriaBuilder.lessThan(path, wrapper.getValue().toString());
                case GREATERTHAN:
                    return criteriaBuilder.equal(path, wrapper.getValue());
                default:
                    return criteriaBuilder.equal(path, wrapper.getValue());
            }

    }
}
