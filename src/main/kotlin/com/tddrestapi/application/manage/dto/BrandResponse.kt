package com.tddrestapi.application.manage.dto

import com.tddrestapi.domain.brand.entity.Brand
import io.swagger.v3.oas.annotations.media.Schema

data class BrandResponse(
    @Schema(description = "브랜드 ID")
    val id: Int,

    @Schema(description = "브랜드 이름")
    val name: String,

    @Schema(description = "브랜드 코드")
    val code: String
) {
    companion object {
        fun from(brand: Brand): BrandResponse {
            return BrandResponse(
                id = brand.id!!,
                name = brand.name,
                code = brand.code
            )
        }
    }
}
