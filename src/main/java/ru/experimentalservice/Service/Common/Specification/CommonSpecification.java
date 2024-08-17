package ru.experimentalservice.Service.Common.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public class CommonSpecification<T> implements Specification<T> {

    private List<SearchCriteria> list;

    public CommonSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : list) {
            Predicate predicate = getPredicate(root, builder, criteria);
            if (predicate != null) {
                predicates.add(predicate);
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getPredicate(Root<T> root, CriteriaBuilder builder, SearchCriteria criteria) {

        if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
            return builder.greaterThan(root.get(criteria.getKey()), (Comparable)criteria.getValue());

        } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
            return builder.lessThan(root.get(criteria.getKey()), (Comparable)criteria.getValue());

        } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Comparable)criteria.getValue());

        } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Comparable)criteria.getValue());

        } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
            return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());

        } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());

        } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
            return builder.like(builder.lower(root.get(criteria.getKey())),
                    "%" + criteria.getValue().toString().toLowerCase() + "%");

        } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
            return builder.like(
                    builder.lower(root.get(criteria.getKey())),
                    criteria.getValue().toString().toLowerCase() + "%");

        } else if (criteria.getOperation().equals(SearchOperation.MATCH_START)) {
            return builder.like(
                    builder.lower(root.get(criteria.getKey())),
                    "%" + criteria.getValue().toString().toLowerCase());

        } else if (criteria.getOperation().equals(SearchOperation.IN)) {
            return builder.in(root.get(criteria.getKey())).value(criteria.getValue());

        } else if (criteria.getOperation().equals(SearchOperation.NOT_IN)) {
            return builder.not(root.get(criteria.getKey())).in(criteria.getValue());

        } else if (criteria.getOperation().equals(SearchOperation.OR)) {
            if (criteria.getValue() instanceof Collection) {
                List<Predicate> orPredicates = new ArrayList<Predicate>();
                Collection<?> collection = (Collection<?>)criteria.getValue();

                for (Object entity : collection) {
                    if (entity instanceof SearchCriteria) {
                        Predicate orPredicate = getPredicate(root, builder, (SearchCriteria)entity);
                        if (orPredicate != null) {
                            orPredicates.add(orPredicate);
                        }
                    }
                }

                if (!orPredicates.isEmpty()) {
                    return builder.or(orPredicates.toArray(new Predicate[0]));
                }
            }
        }

        return null;
    }
}
