package com.tddrestapi.support.exception

import com.tddrestapi.support.annotation.ResponseError
import com.tddrestapi.support.response.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseError(errorCode = ErrorCode.NOT_FOUND_CATEGORY_PRODUCT)
class CategoryProductNotFoundException(category: String) :
    ServiceException("카테고리 [$category] 에 해당하는 상품이 없습니다.")
