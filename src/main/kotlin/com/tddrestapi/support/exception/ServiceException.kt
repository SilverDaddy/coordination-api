package com.tddrestapi.support.exception

import com.tddrestapi.support.annotation.ResponseError
import com.tddrestapi.support.response.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

open class ServiceException : RuntimeException {

    constructor(e: Exception?) : super(e)

    constructor(message: String) : super(message)

    fun getErrorCode(): ErrorCode {
        return this.javaClass.getAnnotation(ResponseError::class.java).errorCode
    }

    fun getResponseStatus(): HttpStatus {
        return this.javaClass.getAnnotation(ResponseStatus::class.java).value
    }
}
