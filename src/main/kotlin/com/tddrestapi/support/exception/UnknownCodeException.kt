package com.tddrestapi.support.exception

import com.tddrestapi.support.annotation.ResponseError
import com.tddrestapi.support.response.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseError(ErrorCode.UNKNOWN_CODE)
class UnknownCodeException(e: RuntimeException?) : ServiceException(e)
