package com.tddrestapi.support.exception

import com.tddrestapi.support.annotation.ResponseError
import com.tddrestapi.support.response.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseError(ErrorCode.RESOURCE_NOT_FOUND)
class ResourceNotFoundException(e: RuntimeException?) : ServiceException(e) {
    constructor(resourceName: String, fieldName: String, fieldValue: Any) : this(
        IllegalStateException(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue))
    )
}
