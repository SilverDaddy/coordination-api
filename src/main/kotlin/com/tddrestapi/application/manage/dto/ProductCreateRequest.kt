package com.tddrestapi.application.manage.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size

data class ProductCreateRequest(
    @field:NotBlank(message = "상품명은 필수입니다.")
    @field:Size(max = 100, message = "상품명은 최대 100자까지 입력할 수 있습니다.")
    @Schema(description = "상품 이름", example = "나이키 티셔츠")
    val name: String,

    @field:NotBlank(message = "브랜드 코드는 필수입니다.")
    @field:Size(max = 50, message = "브랜드 코드는 최대 50자까지 입력할 수 있습니다.")
    @Schema(description = "브랜드 코드", example = "A")
    val brandCode: String,

    @field:NotBlank(message = "카테고리 코드는 필수입니다.")
    @field:Size(max = 50, message = "카테고리 코드는 최대 50자까지 입력할 수 있습니다.")
    @Schema(description = "카테고리 코드", example = "TOP")
    val categoryCode: String,

    @field:NotNull(message = "가격은 필수입니다.")
    @field:PositiveOrZero(message = "가격은 0 이상이어야 합니다.")
    @Schema(description = "상품 가격", example = "50000")
    val price: Int
)
