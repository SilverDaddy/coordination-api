package com.tddrestapi.application.manage.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size

data class CategoryUpdateRequest(
    @field:Size(max = 50, message = "카테고리 라벨은 최대 50자까지 입력할 수 있습니다.")
    @Schema(description = "카테고리 라벨", example = "레인 부츠")
    val label: String? = null,

    @field:Size(max = 50, message = "카테고리 코드는 최대 50자까지 입력할 수 있습니다.")
    @Schema(description = "카테고리 코드", example = "CATEGORY002")
    val code: String? = null
)
