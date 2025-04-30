package com.tddrestapi.application.coordination.service

import com.tddrestapi.application.coordination.dto.*
import com.tddrestapi.domain.category.repository.CategoryRepository
import com.tddrestapi.domain.product.repository.ProductRepository
import com.tddrestapi.support.exception.CoordinationBrandNotFoundException
import com.tddrestapi.support.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CoordinationService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) {

    /**
     * 각 카테고리별로 최저 가격의 상품 정보를 요약하여 반환한다.
     * - Product 테이블에서 카테고리별 최저가 상품들을 조회한다.
     * - 각 카테고리별 최저가 상품 중 첫 번째 상품의 브랜드 및 가격 정보를 담는다.
     * - 전체 카테고리별 최저가 상품 가격을 합산하여 총액을 계산한다.
     */
    fun getCategoryCheapestSummary(): CategoryPriceResponse {
        val cheapestProducts = productRepository.findAllLowestPriceProductsGroupedByCategory()

        val categorySummaries = cheapestProducts.groupBy { it.category }
            .map { (category, products) ->
                val cheapest = products.first()
                CategoryPriceSummary(
                    category = category.label,
                    cheapestBrand = cheapest.brand.name,
                    cheapestPrice = cheapest.price
                )
            }

        val cheapestTotalPrice = categorySummaries.sumOf { it.cheapestPrice }

        return CategoryPriceResponse(
            cheapestTotalPrice = cheapestTotalPrice,
            categorySummaries = categorySummaries
        )
    }

    /**
     * 모든 카테고리를 커버할 수 있는 브랜드 중에서 총 가격이 가장 낮은 브랜드를 찾는다.
     * - 삭제되지 않은 전체 카테고리를 조회하여 기준으로 사용한다.
     * - 브랜드와 카테고리 조합으로 최소 가격의 상품만 조회하여 브랜드별로 그룹화한다.
     * - 각 브랜드가 모든 카테고리에 대한 상품을 보유하고 있는지 검사한다.
     * - 보유 중이라면 해당 브랜드가 각 카테고리별로 보유한 최저가 상품의 가격 총합을 계산한다.
     * - 총합이 가장 낮은 브랜드를 선정하여 결과로 반환한다.
     */
    fun getCheapestBrandForAllCategories(): BrandCheapestDetailResponse {
        val activeCategories = categoryRepository.findAllByDeletedFalseOrderById()
        val products = productRepository.findMinPriceProductsGroupedByBrandAndCategory()
        val productsByBrand = products.groupBy { it.brand }

        val brandCandidates = productsByBrand
            .filter { (_, products) ->
                products.map { it.category }.toSet().containsAll(activeCategories.toSet())
            }
            .map { (brand, products) ->
                val categoryPrices = activeCategories.map { category ->
                    val product = products.find { it.category == category }
                    BrandCategoryPrice(
                        category = category.label,
                        price = product!!.price
                    )
                }
                Triple(brand, categoryPrices, categoryPrices.sumOf { it.price })
            }

        val cheapest = brandCandidates.minByOrNull { it.third }
            ?: throw CoordinationBrandNotFoundException()

        return BrandCheapestDetailResponse(
            cheapestBrand = cheapest.first.name,
            categories = cheapest.second,
            totalPrice = cheapest.third
        )
    }

    /**
     * 특정 카테고리에서 가격이 가장 낮은 브랜드와 가장 높은 브랜드 목록을 조회한다.
     * - 카테고리 라벨로 카테고리 엔티티를 조회한다.
     * - 해당 카테고리의 최저가 및 최고가 상품들을 조회한다.
     * - 각각의 상품 목록을 브랜드와 가격으로 매핑하여 결과로 반환한다.
     */
    fun getExtremeBrandPricesByCategory(categoryLabel: String): CategoryPriceExtremeResponse {
        val category = categoryRepository.findByLabelAndDeletedFalse(categoryLabel)
            ?: throw ResourceNotFoundException("Category", "label", categoryLabel)

        val lowestProducts = productRepository.findLowestPriceProductsByCategory(category)
        val highestProducts = productRepository.findHighestPriceProductsByCategory(category)

        if (lowestProducts.isEmpty() || highestProducts.isEmpty()) {
            throw ResourceNotFoundException("Product", "categoryId", category.id!!)
        }

        val lowest = lowestProducts.map { BrandPriceInfo(brand = it.brand.name, price = it.price) }
        val highest = highestProducts.map { BrandPriceInfo(brand = it.brand.name, price = it.price) }

        return CategoryPriceExtremeResponse(
            category = category.label,
            lowest = lowest,
            highest = highest
        )
    }
}

