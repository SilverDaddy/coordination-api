package com.tddrestapi.application.coordination.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "카테고리 기준 최저 가격 요약 결과")
data class CategoryPriceResponse(
    @Schema(description = "모든 카테고리 최저가격 총합")
    val cheapestTotalPrice: Int,

    @Schema(description = "카테고리별 최저가격 요약 리스트")
    val categorySummaries: List<CategoryPriceSummary>
)
