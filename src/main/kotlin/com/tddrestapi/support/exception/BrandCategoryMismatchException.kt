package com.tddrestapi.support.exception

import com.tddrestapi.support.annotation.ResponseError
import com.tddrestapi.support.response.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseError(errorCode = ErrorCode.BRAND_CATEGORY_MISMATCH)
class BrandCategoryMismatchException(brand: String) :
    ServiceException("브랜드 [$brand] 는 모든 카테고리를 포함하지 않습니다.")
