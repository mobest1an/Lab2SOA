package com.iver.common.utils

import org.springframework.data.domain.Sort

fun checkAscOrDesc(sortParam: String): Sort {
    if (sortParam.split(" ").size > 1) {
        if (sortParam.split(" ")[1].isNotBlank()) {
            return Sort.by(Sort.Direction.DESC, sortParam)
        }
    }
    return Sort.by(Sort.Direction.ASC, sortParam)
}
