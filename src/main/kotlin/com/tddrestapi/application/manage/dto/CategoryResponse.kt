package com.tddrestapi.application.manage.dto

import com.tddrestapi.domain.category.entity.Category
import io.swagger.v3.oas.annotations.media.Schema

data class CategoryResponse(
    @Schema(description = "카테고리 ID")
    val id: Int,

    @Schema(description = "카테고리 라벨")
    val label: String,

    @Schema(description = "카테고리 코드")
    val code: String
) {
    companion object {
        fun from(category: Category): CategoryResponse {
            return CategoryResponse(
                id = category.id!!,
                label = category.label,
                code = category.code
            )
        }
    }
}
