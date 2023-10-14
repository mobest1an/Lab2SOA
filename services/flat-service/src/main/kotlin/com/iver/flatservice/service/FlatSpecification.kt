package com.iver.flatservice.service

import com.iver.common.model.*
import com.iver.flatservice.utils.SearchCriteria
import com.iver.flatservice.utils.SearchOperation
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.*


class FlatSpecification(private val searchCriteria: SearchCriteria) : Specification<Flat> {

    override fun toPredicate(root: Root<Flat>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
        val strToSearch = searchCriteria.value.toString()
        val baseKey = searchCriteria.key.split(".")[0]

        val predicates = mutableListOf<Predicate>()
        when (root.get<Any>(baseKey).javaType) {
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
            House::class.java -> {
                val houseJoin = root.join<Flat, House>("house")
                val nestedKey = searchCriteria.key.split(".")[1]
                predicates.add(criteriaBuilder.equal(houseJoin.get<Any>(nestedKey), strToSearch))
                predicates.add(criteriaBuilder.notEqual(houseJoin.get<Any>(nestedKey), strToSearch))
                predicates.add(criteriaBuilder.greaterThan(houseJoin.get(nestedKey), strToSearch))
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(houseJoin.get(nestedKey), strToSearch))
                predicates.add(criteriaBuilder.lessThan(houseJoin.get(nestedKey), strToSearch))
                predicates.add(criteriaBuilder.lessThanOrEqualTo(houseJoin.get(nestedKey), strToSearch))
            }
            Coordinates::class.java -> {
                val coordinatesJoin = root.join<Flat, Coordinates>("coordinates")
                val nestedKey = searchCriteria.key.split(".")[1]
                predicates.add(criteriaBuilder.equal(coordinatesJoin.get<Any>(nestedKey), strToSearch))
                predicates.add(criteriaBuilder.notEqual(coordinatesJoin.get<Any>(nestedKey), strToSearch))
                predicates.add(criteriaBuilder.greaterThan(coordinatesJoin.get(nestedKey), strToSearch))
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(coordinatesJoin.get(nestedKey), strToSearch))
                predicates.add(criteriaBuilder.lessThan(coordinatesJoin.get(nestedKey), strToSearch))
                predicates.add(criteriaBuilder.lessThanOrEqualTo(coordinatesJoin.get(nestedKey), strToSearch))
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
