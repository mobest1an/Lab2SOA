package com.iver.flatservice.service

import com.iver.common.model.Flat
import com.iver.common.model.Furnish
import com.iver.common.model.Transport
import com.iver.common.model.View
import com.iver.flatservice.utils.SearchCriteria
import com.iver.flatservice.utils.SearchOperation
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class FlatSpecification(private val searchCriteria: SearchCriteria) : Specification<Flat> {

    override fun toPredicate(root: Root<Flat>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
        val strToSearch = searchCriteria.value.toString()

        val predicates = mutableListOf<Predicate>()
        when (root.get<Any>(searchCriteria.key).javaType) {
            Furnish.FINE.javaClass -> {
                predicates.add(criteriaBuilder.equal(root.get<Any>(searchCriteria.key), Furnish.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.notEqual(root.get<Any>(searchCriteria.key), Furnish.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.greaterThan(root.get(searchCriteria.key), Furnish.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.key), Furnish.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.lessThan(root.get(searchCriteria.key), Furnish.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.key), Furnish.valueOf(strToSearch)))
            }
            Transport.ENOUGH.javaClass -> {
                predicates.add(criteriaBuilder.equal(root.get<Any>(searchCriteria.key), Transport.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.notEqual(root.get<Any>(searchCriteria.key), Transport.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.greaterThan(root.get(searchCriteria.key), Transport.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.key), Transport.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.lessThan(root.get(searchCriteria.key), Transport.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.key), Transport.valueOf(strToSearch)))
            }
            View.NORMAL.javaClass -> {
                predicates.add(criteriaBuilder.equal(root.get<Any>(searchCriteria.key), View.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.notEqual(root.get<Any>(searchCriteria.key), View.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.greaterThan(root.get(searchCriteria.key), View.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.key), View.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.lessThan(root.get(searchCriteria.key), View.valueOf(strToSearch)))
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.key), View.valueOf(strToSearch)))
            }
            else -> {
                predicates.add(criteriaBuilder.equal(root.get<Any>(searchCriteria.key), strToSearch))
                predicates.add(criteriaBuilder.notEqual(root.get<Any>(searchCriteria.key), strToSearch))
                predicates.add(criteriaBuilder.greaterThan(root.get(searchCriteria.key), strToSearch))
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.key), strToSearch))
                predicates.add(criteriaBuilder.lessThan(root.get(searchCriteria.key), strToSearch))
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.key), strToSearch))
            }
        }

        return when (Objects.requireNonNull(
            SearchOperation.getSimpleOperation(
                searchCriteria.operation
            )
        )) {
            SearchOperation.EQUAL -> {
                predicates[0]
            }
            SearchOperation.NOT_EQUAL -> {
                predicates[1]
            }
            SearchOperation.GREATER_THAN -> {
                predicates[2]
            }
            SearchOperation.GREATER_THAN_EQUAL -> {
                predicates[3]
            }
            SearchOperation.LESS_THAN -> {
                predicates[4]
            }
            SearchOperation.LESS_THAN_EQUAL -> {
                predicates[5]
            }
            else -> {
                null
            }
        }
    }
}
