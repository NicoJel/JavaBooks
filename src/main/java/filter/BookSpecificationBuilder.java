package filter;

import entity.BookEntity;
import entity.BookEntity_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class BookSpecificationBuilder {


    public Specification<BookEntity> buildClassicBookRequest(BookFilter bookFilter) {
        return Specification.where(withNameOperator(bookFilter.getName()))
                .and(withAuthorOperator(bookFilter.getAuthor()))
                .and(withYearOperator(bookFilter.getYear()))
                .and(withPagesOperator(bookFilter.getPages()))
                .and(withGlobalOperator(bookFilter.getGlobal()));
    }

    private Specification<BookEntity> withGlobalOperator(FilterWrapper<String> global) {
        return Specification.where(withNameOperator(global))
                .or(withAuthorOperator(global));
    }

    public Specification<BookEntity> buildOrBookRequest(BookFilter bookFilter) {
        return Specification.where(withNameOperator(bookFilter.getName())).or(withAuthorOperator(bookFilter.getAuthor()));
    }


    private Specification<BookEntity> withNameOperator(FilterWrapper<String> name) {
        if (name==null)
            return null;
        return new Specification<BookEntity>() {
            @Override
            public Predicate toPredicate(Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return getPredicate(root, root.get(BookEntity_.name), criteriaBuilder, name);
            }
        };
    }

    private Specification<BookEntity> withAuthorOperator(FilterWrapper<String> author) {
        if (author==null)
            return null;
        return new Specification<BookEntity>() {
            @Override
            public Predicate toPredicate(Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return getPredicate(root, root.get(BookEntity_.author), criteriaBuilder, author);
            }
        };
    }

    private Specification<BookEntity> withYearOperator(FilterWrapper<Integer> year) {
        if (year==null)
            return null;
        return new Specification<BookEntity>() {
            @Override
            public Predicate toPredicate(Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return getPredicate(root, root.get(BookEntity_.year), criteriaBuilder, year);
            }
        };
    }

    private Specification<BookEntity> withPagesOperator(FilterWrapper<Integer> pages) {
        if (pages==null)
            return null;
        return new Specification<BookEntity>() {
            @Override
            public Predicate toPredicate(Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return getPredicate(root, root.get(BookEntity_.pages), criteriaBuilder, pages);
            }
        };
    }


    private Predicate getPredicate(Root<BookEntity> root, Path path, CriteriaBuilder criteriaBuilder, FilterWrapper wrapper) {
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