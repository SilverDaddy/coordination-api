package com.tddrestapi.application.coordination

import com.tddrestapi.application.coordination.service.CoordinationService
import com.tddrestapi.domain.brand.entity.Brand
import com.tddrestapi.domain.category.entity.Category
import com.tddrestapi.domain.category.repository.CategoryRepository
import com.tddrestapi.domain.product.entity.Product
import com.tddrestapi.domain.product.repository.ProductRepository
import com.tddrestapi.support.exception.CoordinationBrandNotFoundException
import com.tddrestapi.support.exception.ResourceNotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CoordinationServiceTest : StringSpec({

    val productRepository = mockk<ProductRepository>()
    val categoryRepository = mockk<CategoryRepository>()
    val coordinationService = CoordinationService(productRepository, categoryRepository)

    "카테고리별 최저 가격 상품 요약 조회 성공" {
        val brand = Brand(name = "브랜드1", code = "BR1").apply { id = 1 }
        val category = Category(label = "상의", code = "TOP").apply { id = 1 }
        val product =
            Product(name = "티셔츠", modelNumber = "M-001", price = 10000, brand = brand, category = category).apply {
                id = 1
            }

        every { productRepository.findAllLowestPriceProductsGroupedByCategory() } returns listOf(product)

        val result = coordinationService.getCategoryCheapestSummary()

        result.cheapestTotalPrice shouldBe 10000
        result.categorySummaries shouldHaveSize 1
        result.categorySummaries[0].category shouldBe "상의"
        result.categorySummaries[0].cheapestBrand shouldBe "브랜드1"
    }

    "단일 브랜드 최저 총액 조회 성공" {
        val brand = Brand(name = "브랜드1", code = "BR1").apply { id = 1 }
        val category = Category(label = "상의", code = "TOP").apply { id = 1 }
        val product =
            Product(name = "티셔츠", modelNumber = "M-001", price = 10000, brand = brand, category = category).apply {
                id = 1
            }

        every { categoryRepository.findAllByDeletedFalseOrderById() } returns listOf(category)
        every { productRepository.findMinPriceProductsGroupedByBrandAndCategory() } returns listOf(product)

        val result = coordinationService.getCheapestBrandForAllCategories()

        result.cheapestBrand shouldBe "브랜드1"
        result.totalPrice shouldBe 10000
    }

    "단일 브랜드 최저 총액 조회 실패 - 조건 충족 브랜드 없음" {
        val category = Category(label = "상의", code = "TOP").apply { id = 1 }

        every { categoryRepository.findAllByDeletedFalseOrderById() } returns listOf(category)
        every { productRepository.findMinPriceProductsGroupedByBrandAndCategory() } returns emptyList()

        shouldThrow<CoordinationBrandNotFoundException> {
            coordinationService.getCheapestBrandForAllCategories()
        }
    }

    "특정 카테고리 기준 최저 최고 가격 조회 성공" {
        val brand = Brand(name = "브랜드1", code = "BR1").apply { id = 1 }
        val category = Category(label = "상의", code = "TOP").apply { id = 1 }
        val product =
            Product(name = "티셔츠", modelNumber = "M-001", price = 10000, brand = brand, category = category).apply {
                id = 1
            }

        every { categoryRepository.findByLabelAndDeletedFalse("상의") } returns category
        every { productRepository.findLowestPriceProductsByCategory(category) } returns listOf(product)
        every { productRepository.findHighestPriceProductsByCategory(category) } returns listOf(product)

        val result = coordinationService.getExtremeBrandPricesByCategory("상의")

        result.category shouldBe "상의"
        result.lowest shouldHaveSize 1
        result.highest shouldHaveSize 1
    }

    "특정 카테고리 기준 최저 최고 가격 조회 실패 - 없는 카테고리" {
        every { categoryRepository.findByLabelAndDeletedFalse("바지") } returns null

        shouldThrow<ResourceNotFoundException> {
            coordinationService.getExtremeBrandPricesByCategory("바지")
        }
    }
})
