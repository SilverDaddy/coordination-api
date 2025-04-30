package com.tddrestapi.application.manage.service

import com.tddrestapi.application.manage.dto.ProductCreateRequest
import com.tddrestapi.domain.brand.entity.Brand
import com.tddrestapi.domain.category.entity.Category
import com.tddrestapi.domain.product.repository.ProductRepository
import com.tddrestapi.support.exception.ResourceNotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class ProductManageServiceTest : StringSpec({

    val productRepository = mockk<ProductRepository>()
    val brandManageService = mockk<BrandManageService>()
    val categoryManageService = mockk<CategoryManageService>()

    val productManageService = ProductManageService(
        productRepository,
        brandManageService,
        categoryManageService
    )

    "상품을 정상적으로 생성할 수 있다" {
        // given
        val brand = Brand(id = 1, name = "Nike", code = "NIKE")
        val category = Category(id = 1, label = "신발", code = "SHOES")

        val request = ProductCreateRequest(
            name = "에어포스 1",
            brandCode = "NIKE",
            categoryCode = "SHOES",
            price = 120000
        )

        every { brandManageService.findByCodeOrThrow("NIKE") } returns brand
        every { categoryManageService.findByCodeOrThrow("SHOES") } returns category
        every { productRepository.countByBrandAndCategory(brand, category) } returns 0
        every { productRepository.save(any()) } answers { firstArg() }

        // when
        val result = productManageService.createProduct(request)

        // then
        result.name shouldBe "에어포스 1"
        result.brand shouldBe brand
        result.category shouldBe category
        result.price shouldBe 120000
        result.modelNumber shouldBe "NIKE-SHOES-001"

        verify(exactly = 1) { productRepository.save(any()) }
    }

    "존재하지 않는 브랜드로 상품을 생성하면 예외가 발생한다" {
        // given
        val request = ProductCreateRequest(
            name = "에어포스 1",
            brandCode = "UNKNOWN",
            categoryCode = "SHOES",
            price = 120000
        )

        every { brandManageService.findByCodeOrThrow("UNKNOWN") } throws ResourceNotFoundException(
            "Brand",
            "code",
            "UNKNOWN"
        )

        // when & then
        shouldThrow<ResourceNotFoundException> {
            productManageService.createProduct(request)
        }
    }

    "존재하지 않는 카테고리로 상품을 생성하면 예외가 발생한다" {
        // given
        val brand = Brand(id = 1, name = "Nike", code = "NIKE")

        val request = ProductCreateRequest(
            name = "에어포스 1",
            brandCode = "NIKE",
            categoryCode = "UNKNOWN",
            price = 120000
        )

        every { brandManageService.findByCodeOrThrow("NIKE") } returns brand
        every { categoryManageService.findByCodeOrThrow("UNKNOWN") } throws ResourceNotFoundException(
            "Category",
            "code",
            "UNKNOWN"
        )

        // when & then
        shouldThrow<ResourceNotFoundException> {
            productManageService.createProduct(request)
        }
    }
})
