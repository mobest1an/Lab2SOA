package com.iver.flatejb.utils

enum class SortingType {
    ASC,
    DESC,
}

class SortingParameters(
    val fieldName: String?,
    val nestedFieldName: String?,
    val sortingType: SortingType?,
    val nested: Boolean = false,
)

fun checkAscOrDesc(sortParam: String): String {
    if (sortParam.split(" ").size > 1) {
        if (sortParam.split(" ")[1].isNotBlank()) {
            return "${sortParam.split(" ")[0]} DESC"
        }
    }
    return "$sortParam ASC"
}
