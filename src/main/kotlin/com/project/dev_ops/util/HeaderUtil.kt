package com.project.dev_ops.util

import org.springframework.http.HttpHeaders


class HeaderUtil {
    companion object {
        //First param is token
        fun createHttpHeaders(vararg param: String): HttpHeaders {
            val headers = HttpHeaders()
            headers["Authorization"] = param[0]
            return headers
        }
    }
}