package com.tddrestapi.ui.admin

import com.tddrestapi.application.manage.dto.CategoryCreateRequest
import com.tddrestapi.application.manage.dto.CategoryResponse
import com.tddrestapi.application.manage.dto.CategoryUpdateRequest
import com.tddrestapi.application.manage.service.CategoryManageService
import com.tddrestapi.support.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/api/admin/v1/categories")
@Tag(name = "3. 카테고리 관리", description = "카테고리 생성, 수정, 삭제, 조회 API")
class CategoryManageController(
    private val categoryManageService: CategoryManageService
) {
    @Operation(summary = "카테고리 목록 조회", description = "등록된 모든 카테고리를 조회합니다.")
    @GetMapping
    fun getCategories(): ApiResponse<List<CategoryResponse>> {
        val categories = categoryManageService.getAllCategories()
        return ApiResponse.success(categories)
    }

    @Operation(summary = "카테고리 생성", description = "신규 카테고리를 등록합니다.")
    @PostMapping
    fun createCategory(@Valid @RequestBody request: CategoryCreateRequest): ApiResponse<CategoryResponse> {
        return ApiResponse.success(CategoryResponse.from(categoryManageService.createCategory(request)))
    }

    @Operation(summary = "카테고리 수정", description = "기존 카테고리 정보를 수정합니다.")
    @PutMapping("/{id}")
    fun updateCategory(
        @Parameter(description = "카테고리 ID") @Min(1) @PathVariable id: Int,
        @Valid @RequestBody request: CategoryUpdateRequest
    ): ApiResponse<CategoryResponse> {
        return ApiResponse.success(CategoryResponse.from(categoryManageService.updateCategory(id, request)))
    }

    @Operation(summary = "카테고리 삭제", description = "카테고리를 삭제 처리합니다.")
    @DeleteMapping("/{id}")
    fun deleteCategory(
        @Parameter(description = "카테고리 ID") @Min(1) @PathVariable id: Int
    ): ApiResponse<Unit> {
        categoryManageService.deleteCategory(id)
        return ApiResponse.success()
    }
}
