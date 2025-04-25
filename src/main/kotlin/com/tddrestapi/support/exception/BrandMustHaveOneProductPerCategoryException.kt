package com.tddrestapi.support.exception

import com.tddrestapi.support.annotation.ResponseError
import com.tddrestapi.support.response.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseError(errorCode = ErrorCode.BRAND_CATEGORY_MISMATCH)
class BrandMustHaveOneProductPerCategoryException(brandName: String,
                                                  categoryLabel: String) :
    ServiceException("브랜드 [$brandName] 는 카테고리 [$categoryLabel] 에 최소 하나 이상의 상품을 보유해야 합니다.")
