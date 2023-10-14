package com.iver.flatservice.utils

import com.iver.common.model.Flat
import com.iver.flatservice.service.FlatSpecification
import org.springframework.data.jpa.domain.Specification

enum class SearchOperation {
    EQUAL, NOT_EQUAL, GREATER_THAN, GREATER_THAN_EQUAL, LESS_THAN, LESS_THAN_EQUAL;

    companion object {
        val SIMPLE_OPERATION_SET = arrayOf(
            "eq", "ne", "gt", "ge", "lt", "le"
        )

        fun getSimpleOperation(
            input: String?
        ): SearchOperation? {
            return when (input) {
                "eq" -> EQUAL
                "ne" -> NOT_EQUAL
                "gt" -> GREATER_THAN
                "ge" -> GREATER_THAN_EQUAL
                "lt" -> LESS_THAN
                "le" -> LESS_THAN_EQUAL
                else -> null
            }
        }
    }
}

class SearchCriteria(
    val key: String,
    val operation: String,
    val value: Any,
)

class FlatSpecificationBuilder {
    private val params = mutableListOf<SearchCriteria>()

    fun parseCriteria(filters: Array<String>) {
        for (filter in filters) {
            val key = filter.split("[")[0]
            val operation = filter.split("[")[1].split("]")[0]
            val value = filter.split("[")[1].split("]")[1] as Any
            with(key, operation, value)
        }
    }

    fun with(
        key: String,
        operation: String,
        value: Any
    ): FlatSpecificationBuilder {
        params.add(SearchCriteria(key, operation, value))
        return this
    }

    fun with(searchCriteria: SearchCriteria): FlatSpecificationBuilder {
        params.add(searchCriteria)
        return this
    }

    fun build(): Specification<Flat>? {
        if (params.size == 0) {
            return null
        }
        var result: Specification<Flat> = FlatSpecification(params[0])
        for (idx in 1 until params.size) {
            val criteria = params[idx]
            result = Specification.where(result).and(FlatSpecification(criteria))
        }
        return result
    }
}
