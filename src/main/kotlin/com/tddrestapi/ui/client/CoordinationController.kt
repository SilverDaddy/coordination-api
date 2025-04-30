package com.tddrestapi.ui.client

import com.tddrestapi.application.coordination.dto.BrandCheapestDetailResponse
import com.tddrestapi.application.coordination.dto.CategoryPriceExtremeResponse
import com.tddrestapi.application.coordination.dto.CategoryPriceResponse
import com.tddrestapi.application.coordination.service.CoordinationService
import com.tddrestapi.support.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/api/client/v1/coordination")
@Tag(name = "4. 회원 코디네이션 서비스", description = "브랜드/카테고리 가격 분석 관련 API")
class CoordinationController(
    private val coordinationService: CoordinationService
) {

    @Operation(summary = "카테고리 별 최저가격 상품 조회", description = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회합니다.")
    @GetMapping("/category-summary")
    fun getCategoryCheapestSummary(): ApiResponse<CategoryPriceResponse> {
        return ApiResponse.success(coordinationService.getCategoryCheapestSummary())
    }

    @Operation(
        summary = "단일 브랜드 최저가 조회",
        description = "단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회합니다."
    )
    @GetMapping("/cheapest-brand")
    fun getCheapestBrandForAllCategories(): ApiResponse<BrandCheapestDetailResponse> {
        return ApiResponse.success(coordinationService.getCheapestBrandForAllCategories())
    }

    @Operation(summary = "특정 카테고리의 최고/최저 가격 브랜드 조회", description = "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회합니다.")
    @GetMapping("/category-extreme")
    fun getExtremeBrandPricesByCategory(
        @Parameter(description = "카테고리 이름", example = "상의")
        @RequestParam("category") @NotBlank category: String
    ): ApiResponse<CategoryPriceExtremeResponse> {
        return ApiResponse.success(coordinationService.getExtremeBrandPricesByCategory(category))
    }
}
