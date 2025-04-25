package com.tddrestapi.support.exception

import com.tddrestapi.support.annotation.ResponseError
import com.tddrestapi.support.response.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseError(ErrorCode.INVALID_PARAMETER)
class InvalidParameterException(e: RuntimeException?) : ServiceException(e) {
    constructor() : this(IllegalArgumentException("잘못된 파라미터입니다."))
}
