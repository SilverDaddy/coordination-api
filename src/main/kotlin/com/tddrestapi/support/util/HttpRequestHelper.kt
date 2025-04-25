package com.tddrestapi.support.util

import com.tddrestapi.support.constant.HEADER.Companion.REQUEST_ID
import org.slf4j.MDC
import java.util.*

class HttpRequestHelper {
    companion object {
        fun clear() {
            MDC.clear()
        }

        fun getRequestId(): String {
            val requestId = MDC.get(REQUEST_ID)
            return if (requestId.isNullOrEmpty()) {
                val newRequestId = UUID.randomUUID().toString()
                MDC.put(REQUEST_ID, newRequestId)
                newRequestId
            } else {
                requestId
            }
        }
    }
}
