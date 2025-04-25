package com.tddrestapi.support.exception

import com.tddrestapi.support.response.ErrorCode
import com.tddrestapi.support.response.ErrorResponse
import com.tddrestapi.support.response.MetaResponse
import com.tddrestapi.support.util.getLogger
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    companion object {
        private val logger = getLogger()
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun serviceExceptionHandler(e: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                error = e.message,
                meta = MetaResponse(
                    responseCode = e.getErrorCode().code,
                    detail = e.getErrorCode().message
                )
            ),
            e.getResponseStatus()
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = HashMap<String, String>()
        e.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            if (errorMessage != null) {
                errors[fieldName] = errorMessage
            }
        }

        return ResponseEntity(
            ErrorResponse(
                meta = MetaResponse(
                    responseCode = ErrorCode.INVALID_PARAMETER.code
                )
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableExceptionHandler(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        logger.error("httpMessageNotReadableExceptionHandler : ${e.message}")
        return ResponseEntity(
            ErrorResponse(
                meta = MetaResponse(
                    responseCode = ErrorCode.INVALID_PARAMETER.code
                )
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(ServiceException::class)
    fun serviceExceptionHandler(e: ServiceException): ResponseEntity<ErrorResponse> {
        logger.error("serviceExceptionHandler : ${e.message}")
        return ResponseEntity(
            ErrorResponse(
                meta = MetaResponse(
                    responseCode = e.getErrorCode().code,
                    detail = e.message ?: e.getErrorCode().message
                )
            ),
            e.getResponseStatus()
        )
    }

    @ExceptionHandler(InvalidParameterException::class)
    fun invalidParameterException(e: InvalidParameterException): ResponseEntity<ErrorResponse> {
        logger.error("invalidParameterException : ${e.message}")
        return ResponseEntity(
            ErrorResponse(
                meta = MetaResponse(
                    responseCode = ErrorCode.INVALID_PARAMETER.code,
                    detail = ErrorCode.INVALID_PARAMETER.message
                )
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationException(e: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        logger.error("constraintViolationException : ${e.message}")
        return ResponseEntity(
            ErrorResponse(
                meta = MetaResponse(
                    responseCode = ErrorCode.INVALID_PARAMETER.code,
                    detail = ErrorCode.INVALID_PARAMETER.message
                )
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.error("Exception : ${ex.message}")
        val errorResponse = ErrorResponse(
            error = ex.message,
            meta = MetaResponse(
                responseCode = ErrorCode.UNKNOWN_ERROR.code,
                detail = ex.localizedMessage
            )
        )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
