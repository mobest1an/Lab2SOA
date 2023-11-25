package com.iver.flatejb.utils

import com.iver.flatejb.model.Flat
import com.iver.flatejb.service.FlatSpecification
import jakarta.persistence.criteria.CriteriaQuery

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

    private fun with(
        key: String,
        operation: String,
        value: Any
    ): FlatSpecificationBuilder {
        params.add(SearchCriteria(key, operation, value))
        return this
    }

    fun build(): CriteriaQuery<Flat>? {
        if (params.size == 0) {
            return null
        }
        var flatSpecification = FlatSpecification(params[0])
        var result = flatSpecification.select.where(flatSpecification.toPredicate())
        for (idx in 1 until params.size) {
            val criteria = params[idx]
            flatSpecification = FlatSpecification(criteria)
            result = flatSpecification.select.where(flatSpecification.toPredicate())
        }
        return result
    }
}
