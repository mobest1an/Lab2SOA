package com.iver.flatservice.service

import com.iver.common.model.*
import com.iver.flatservice.utils.SearchCriteria
import com.iver.flatservice.utils.SearchOperation
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.*

private fun <T : Enum<T>> getInstance(value: String, enumClass: Class<T>): T {
    return java.lang.Enum.valueOf(enumClass, value)
}

private fun <T : Enum<T>> getEnumPredicates(
    criteriaBuilder: CriteriaBuilder,
    root: Root<Flat>,
    key: String,
    value: String,
    enumClass: Class<T>,
): Array<Predicate> {
    val predicates = mutableListOf<Predicate>()
    predicates.add(criteriaBuilder.equal(root.get<Any>(key), getInstance(value, enumClass)))
    predicates.add(criteriaBuilder.notEqual(root.get<Any>(key), getInstance(value, enumClass)))
    predicates.add(criteriaBuilder.greaterThan(root.get(key), getInstance(value, enumClass)))
    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(key), getInstance(value, enumClass)))
    predicates.add(criteriaBuilder.lessThan(root.get(key), getInstance(value, enumClass)))
    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(key), getInstance(value, enumClass)))

    return predicates.toTypedArray()
}

private fun <T : Comparable<T>> getCommonPredicates(
    path: Expression<out T>,
    criteriaBuilder: CriteriaBuilder,
    value: T,
): Array<Predicate> {
    val predicates = mutableListOf<Predicate>()

    predicates.add(criteriaBuilder.equal(path, value))
    predicates.add(criteriaBuilder.notEqual(path, value))
    predicates.add(criteriaBuilder.greaterThan(path, value))
    predicates.add(criteriaBuilder.greaterThanOrEqualTo(path, value))
    predicates.add(criteriaBuilder.lessThan(path, value))
    predicates.add(criteriaBuilder.lessThanOrEqualTo(path, value))

    return predicates.toTypedArray()
}

class FlatSpecification(private val searchCriteria: SearchCriteria) : Specification<Flat> {

    override fun toPredicate(root: Root<Flat>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
        val strToSearch = searchCriteria.value.toString()
        val baseKey = searchCriteria.key.split(".")[0]

        val predicates = mutableListOf<Predicate>()


        when (root.get<Any>(baseKey).javaType) {
            Furnish.FINE.javaClass -> {
                predicates += getEnumPredicates(criteriaBuilder, root, baseKey, strToSearch, Furnish.FINE.javaClass)
            }

            Transport.ENOUGH.javaClass -> {
                predicates += getEnumPredicates(criteriaBuilder, root, baseKey, strToSearch, Transport.ENOUGH.javaClass)
            }

            View.NORMAL.javaClass -> {
                predicates += getEnumPredicates(criteriaBuilder, root, baseKey, strToSearch, View.NORMAL.javaClass)
            }

            House::class.java -> {
                val houseJoin = root.join<Flat, House>("house")
                val nestedKey = searchCriteria.key.split(".")[1]
                predicates += getCommonPredicates(houseJoin.get(nestedKey), criteriaBuilder, strToSearch)
            }

            Coordinates::class.java -> {
                val coordinatesJoin = root.join<Flat, Coordinates>("coordinates")
                val nestedKey = searchCriteria.key.split(".")[1]
                predicates += getCommonPredicates(coordinatesJoin.get(nestedKey), criteriaBuilder, strToSearch)
            }

            else -> {
                predicates += getCommonPredicates(root.get(baseKey), criteriaBuilder, strToSearch)
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
