package com.tddrestapi.support.response

data class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T? = null
) {
    companion object {
        fun <T> success(data: T? = null, code: ApiResponseCode = ApiResponseCode.SUCCESS): ApiResponse<T> =
            ApiResponse(code.name, code.message, data)

        fun fail(code: ApiResponseCode): ApiResponse<Nothing> =
            ApiResponse(code.name, code.message, null)

        fun fail(code: ApiResponseCode, customMessage: String): ApiResponse<Nothing> =
            ApiResponse(code.name, customMessage, null)
    }
}
