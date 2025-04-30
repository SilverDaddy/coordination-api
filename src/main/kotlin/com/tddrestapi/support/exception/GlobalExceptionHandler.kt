package com.tddrestapi.support.exception

import com.tddrestapi.support.response.ApiResponse
import com.tddrestapi.support.response.ErrorCode
import com.tddrestapi.support.response.ErrorResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * 비즈니스 예외(ServiceException) 처리
     */
    @ExceptionHandler(ServiceException::class)
    fun handleServiceException(ex: ServiceException): ResponseEntity<ApiResponse<ErrorResponse>> {
        val errorCode = ex.getErrorCode()
        val httpStatus = ex.getResponseStatus()

        val errorResponse = ErrorResponse(
            code = errorCode.name,
            message = errorCode.message,
            detail = ex.message
        )

        return ResponseEntity
            .status(httpStatus)
            .body(ApiResponse.fail(errorCode, errorResponse))
    }

    /**
     * @Valid 실패 - MethodArgumentNotValidException 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponse<ErrorResponse>> {
        val firstError = ex.bindingResult.fieldErrors.firstOrNull()?.defaultMessage
            ?: ErrorCode.INVALID_PARAMETER.message

        val errorResponse = ErrorResponse(
            code = ErrorCode.INVALID_PARAMETER.name,
            message = ErrorCode.INVALID_PARAMETER.message,
            detail = firstError
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.fail(ErrorCode.INVALID_PARAMETER, errorResponse))
    }

    /**
     * @Validated 실패 - ConstraintViolationException 처리
     */
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<ApiResponse<ErrorResponse>> {
        val firstViolation = ex.constraintViolations.firstOrNull()?.message
            ?: ErrorCode.INVALID_PARAMETER.message

        val errorResponse = ErrorResponse(
            code = ErrorCode.INVALID_PARAMETER.name,
            message = ErrorCode.INVALID_PARAMETER.message,
            detail = firstViolation
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ApiResponse.fail(ErrorCode.INVALID_PARAMETER, errorResponse))
    }

    /**
     * 그 외 모든 예외 처리
     */
    @ExceptionHandler(Exception::class)
    fun handleUnknownException(ex: Exception): ResponseEntity<ApiResponse<ErrorResponse>> {
        val errorResponse = ErrorResponse(
            code = ErrorCode.UNKNOWN_ERROR.name,
            message = ErrorCode.UNKNOWN_ERROR.message,
            detail = ex.message
        )

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse.fail(ErrorCode.UNKNOWN_ERROR, errorResponse))
    }
}
