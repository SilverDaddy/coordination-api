package com.tddrestapi.application.coordination.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "브랜드 가격 정보")
data class BrandPriceInfo(
    @Schema(description = "브랜드명")
    val brand: String,

    @Schema(description = "가격")
    val price: Int
)
