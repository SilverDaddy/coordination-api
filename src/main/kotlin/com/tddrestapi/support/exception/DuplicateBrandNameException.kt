package com.tddrestapi.support.exception

import com.tddrestapi.support.annotation.ResponseError
import com.tddrestapi.support.response.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseError(errorCode = ErrorCode.BRAND_NAME_DUPLICATE)
class DuplicateBrandNameException(brand: String) :
    ServiceException("브랜드 [$brand] 는 이미 존재하는 브랜드 이름입니다.")
