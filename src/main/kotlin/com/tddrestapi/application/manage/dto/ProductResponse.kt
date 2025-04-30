package com.tddrestapi.application.manage.dto

import com.tddrestapi.domain.product.entity.Product
import io.swagger.v3.oas.annotations.media.Schema

data class ProductResponse(
    @Schema(description = "상품 ID")
    val id: Int,

    @Schema(description = "상품 이름")
    val name: String,

    @Schema(description = "모델 번호")
    val modelNumber: String,

    @Schema(description = "상품 가격")
    val price: Int,

    @Schema(description = "브랜드 정보")
    val brand: BrandResponse,

    @Schema(description = "카테고리 정보")
    val category: CategoryResponse
) {
    companion object {
        fun from(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id!!,
                name = product.name,
                modelNumber = product.modelNumber,
                price = product.price,
                brand = BrandResponse.from(product.brand),
                category = CategoryResponse.from(product.category)
            )
        }
    }
}
