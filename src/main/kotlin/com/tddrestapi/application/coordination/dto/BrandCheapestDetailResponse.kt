package com.tddrestapi.application.coordination.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "단일 브랜드로 모든 카테고리 상품 구매 시 최저가격 상세 정보")
data class BrandCheapestDetailResponse(
    @Schema(description = "최저가 브랜드명")
    val cheapestBrand: String,

    @Schema(description = "브랜드별 카테고리 상품 가격 목록")
    val categories: List<BrandCategoryPrice>,

    @Schema(description = "총 상품 가격 합계")
    val totalPrice: Int
)
