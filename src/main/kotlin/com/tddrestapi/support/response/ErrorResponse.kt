package com.tddrestapi.support.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "에러 응답")
data class ErrorResponse(
    @Schema(description = "에러 코드 (Enum 이름)", example = "RESOURCE_NOT_FOUND")
    val code: String,

    @Schema(description = "에러 기본 메시지", example = "처리할 데이터가 존재하지 않습니다.")
    val message: String,

    @Schema(description = "에러 상세 메시지", example = "Product ID 123을 찾을 수 없습니다.")
    val detail: String? = null
)
