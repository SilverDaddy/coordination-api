package com.tddrestapi.support.response

import com.tddrestapi.support.exception.UnknownCodeException

enum class ErrorCode(val status: Int, val message: String) {
    // COMMON
    UNKNOWN_ERROR(-1, "알 수 없는 에러입니다."),
    UNKNOWN_CODE(-2, "알 수 없는 코드입니다."),
    UNKNOWN_EXTERNAL_ERROR(-3, "알 수 없는 에러입니다."),
    SUCCESS(0, "성공"),
    PROCESSING(1, "진행중 입니다"),
    METHOD_NOW_ALLOWED(4, "허용되지 않은 메소드입니다."),

    RESOURCE_NOT_FOUND(100001, "처리할 데이터가 존재하지 않습니다."),
    INVALID_PARAMETER(100002, "잘못된 파라미터입니다."),

    COORDINATION_BRAND_NOT_FOUND(40001, "모든 카테고리를 포함한 브랜드가 없습니다."),
    BRAND_CATEGORY_MISMATCH(40002, "브랜드가 모든 카테고리를 포함하지 않습니다."),
    NOT_FOUND_CATEGORY_PRODUCT(40003, "카테고리에 해당하는 상품이 없습니다."),
    BRAND_NAME_DUPLICATE(40004, "이미 존재하는 브랜드 이름입니다."),
    BRAND_DELETION_NOT_ALLOWED(40005, "브랜드를 삭제할 수 없습니다."),
    BRAND_MUST_HAVE_ONE_PRODUCT_PER_CATEGORY_EXCEPTION(40006, "브랜드는 상품 카테고리에 최소 하나 이상의 상품을 보유해야 합니다."),
    ;

    companion object {
        fun getEnumByCode(status: Int): ErrorCode {
            return entries.find { it.status == status }
                ?: throw UnknownCodeException(IllegalArgumentException(status.toString()))
        }
    }
}
