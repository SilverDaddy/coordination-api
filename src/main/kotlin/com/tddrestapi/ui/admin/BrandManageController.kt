package com.tddrestapi.ui.admin

import com.tddrestapi.application.manage.dto.BrandCreateRequest
import com.tddrestapi.application.manage.dto.BrandResponse
import com.tddrestapi.application.manage.dto.BrandUpdateRequest
import com.tddrestapi.application.manage.service.BrandManageService
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
@RequestMapping("/api/admin/v1/brands")
@Tag(name = "2. 브랜드 관리", description = "브랜드 생성, 수정, 삭제, 조회 API")
class BrandManageController(
    private val brandManageService: BrandManageService
) {
    @Operation(summary = "브랜드 목록 조회", description = "등록된 모든 브랜드를 조회합니다.")
    @GetMapping
    fun getBrands(): ApiResponse<List<BrandResponse>> {
        val brands = brandManageService.getAllBrands()
        return ApiResponse.success(brands)
    }

    @Operation(summary = "브랜드 생성", description = "신규 브랜드를 등록합니다.")
    @PostMapping
    fun createBrand(@Valid @RequestBody request: BrandCreateRequest): ApiResponse<BrandResponse> {
        return ApiResponse.success(BrandResponse.from(brandManageService.createBrand(request)))
    }

    @Operation(summary = "브랜드 수정", description = "기존 브랜드 정보를 수정합니다.")
    @PutMapping("/{id}")
    fun updateBrand(
        @Parameter(description = "브랜드 ID") @Min(1) @PathVariable id: Int,
        @Valid @RequestBody request: BrandUpdateRequest
    ): ApiResponse<BrandResponse> {
        return ApiResponse.success(BrandResponse.from(brandManageService.updateBrand(id, request)))
    }

    @Operation(summary = "브랜드 삭제", description = "브랜드를 삭제 처리합니다.")
    @DeleteMapping("/{id}")
    fun deleteBrand(
        @Parameter(description = "브랜드 ID") @Min(1) @PathVariable id: Int
    ): ApiResponse<Unit> {
        brandManageService.deleteBrand(id)
        return ApiResponse.success()
    }
}
