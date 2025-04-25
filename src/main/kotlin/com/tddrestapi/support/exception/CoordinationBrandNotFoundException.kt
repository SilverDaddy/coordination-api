package com.tddrestapi.support.exception

import com.tddrestapi.support.annotation.ResponseError
import com.tddrestapi.support.response.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseError(errorCode = ErrorCode.INVALID_PARAMETER)
class CoordinationBrandNotFoundException :
    ServiceException(RuntimeException(ErrorCode.COORDINATION_BRAND_NOT_FOUND.message))
