package com.tddrestapi.application.manage.service

import com.tddrestapi.domain.category.entity.Category
import com.tddrestapi.domain.category.repository.CategoryRepository
import com.tddrestapi.support.exception.ResourceNotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CategoryManageServiceTest : StringSpec({

    val categoryRepository = mockk<CategoryRepository>()

    val categoryManageService = CategoryManageService(
        categoryRepository
    )

    "카테고리 코드를 통해 카테고리를 정상 조회할 수 있다" {
        // given
        val category = Category(id = 1, label = "신발", code = "SHOES")

        every { categoryRepository.findByCodeAndDeletedFalse("SHOES") } returns category

        // when
        val result = categoryManageService.findByCodeOrThrow("SHOES")

        // then
        result shouldBe category
    }

    "존재하지 않는 카테고리 코드를 조회하면 예외가 발생한다" {
        // given
        every { categoryRepository.findByCodeAndDeletedFalse("UNKNOWN") } returns null

        // when & then
        shouldThrow<ResourceNotFoundException> {
            categoryManageService.findByCodeOrThrow("UNKNOWN")
        }
    }
})
