package com.tddrestapi.support.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "공통 API 응답 포맷")
data class ApiResponse<T>(
    @field:Schema(description = "응답 상태 코드", example = "0")
    val status: Int,

    @field:Schema(description = "상태 메시지", example = "성공")
    val message: String,

    @field:Schema(description = "응답 데이터", nullable = true)
    val data: T? = null
) {
    companion object {
        fun <T> success(data: T? = null): ApiResponse<T> =
            ApiResponse(
                status = ErrorCode.SUCCESS.status,
                message = ErrorCode.SUCCESS.message,
                data = data
            )

        fun fail(errorCode: ErrorCode, errorResponse: ErrorResponse): ApiResponse<ErrorResponse> =
            ApiResponse(
                status = errorCode.status,
                message = errorCode.message,
                data = errorResponse
            )
    }
}
