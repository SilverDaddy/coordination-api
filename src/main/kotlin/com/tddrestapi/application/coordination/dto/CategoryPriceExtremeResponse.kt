package com.tddrestapi.application.coordination.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "카테고리별 최저/최고 가격 브랜드 조회 결과")
data class CategoryPriceExtremeResponse(
    @Schema(description = "카테고리명")
    val category: String,

    @Schema(description = "최저가 브랜드 리스트")
    val lowest: List<BrandPriceInfo>,

    @Schema(description = "최고가 브랜드 리스트")
    val highest: List<BrandPriceInfo>
)
