package com.tddrestapi.application.coordination.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "카테고리별 최저가격 요약")
data class CategoryPriceSummary(
    @Schema(description = "카테고리명")
    val category: String,

    @Schema(description = "최저가 브랜드명")
    val cheapestBrand: String,

    @Schema(description = "최저 가격")
    val cheapestPrice: Int
)

