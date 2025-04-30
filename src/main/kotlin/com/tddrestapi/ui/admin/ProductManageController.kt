package com.tddrestapi.ui.admin

import com.tddrestapi.application.manage.dto.ProductCreateRequest
import com.tddrestapi.application.manage.dto.ProductResponse
import com.tddrestapi.application.manage.dto.ProductUpdateRequest
import com.tddrestapi.application.manage.service.ProductManageService
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
@RequestMapping("/api/admin/v1/products")
@Tag(name = "1. 상품 관리", description = "상품 생성, 수정, 삭제, 조회 API")
class ProductManageController(
    private val productManageService: ProductManageService
) {
    @Operation(summary = "상품 생성", description = "상품을 새로 등록합니다.")
    @PostMapping
    fun createProduct(@RequestBody @Valid request: ProductCreateRequest): ApiResponse<ProductResponse> {
        val product = productManageService.createProduct(request)
        return ApiResponse.success(ProductResponse.from(product))
    }

    @Operation(summary = "상품 수정", description = "상품 정보를 수정합니다.")
    @PutMapping("/{id}")
    fun updateProduct(
        @Parameter(description = "상품 ID") @PathVariable @Min(1) id: Int,
        @RequestBody @Valid request: ProductUpdateRequest
    ): ApiResponse<ProductResponse> {
        val product = productManageService.updateProduct(id, request)
        return ApiResponse.success(ProductResponse.from(product))
    }

    @Operation(summary = "상품 삭제", description = "상품을 삭제(Soft Delete)합니다.")
    @DeleteMapping("/{id}")
    fun deleteProduct(
        @Parameter(description = "상품 ID") @PathVariable @Min(1) id: Int
    ): ApiResponse<Unit> {
        productManageService.deleteProduct(id)
        return ApiResponse.success()
    }

    @Operation(summary = "상품 목록 조회", description = "전체 상품 리스트를 조회합니다.")
    @GetMapping
    fun getAllProducts(): ApiResponse<List<ProductResponse>> {
        val products = productManageService.getAllProducts()
        return ApiResponse.success(products)
    }
}
