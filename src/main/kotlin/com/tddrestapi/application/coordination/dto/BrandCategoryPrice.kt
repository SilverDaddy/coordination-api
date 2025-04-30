package com.tddrestapi.application.coordination.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "브랜드 카테고리별 상품 가격")
data class BrandCategoryPrice(
    @Schema(description = "카테고리명")
    val category: String,

    @Schema(description = "가격")
    val price: Int
)
