package com.tddrestapi.application.manage.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size

data class BrandUpdateRequest(
    @field:Size(max = 100, message = "브랜드 이름은 최대 100자까지 입력할 수 있습니다.")
    @Schema(description = "브랜드 이름", example = "아디다스")
    val name: String? = null,

    @field:Size(max = 50, message = "브랜드 코드는 최대 50자까지 입력할 수 있습니다.")
    @Schema(description = "브랜드 코드", example = "BRAND002")
    val code: String? = null
)
