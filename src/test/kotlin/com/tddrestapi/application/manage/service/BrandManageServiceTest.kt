package com.tddrestapi.application.manage.service

import com.tddrestapi.domain.brand.entity.Brand
import com.tddrestapi.domain.brand.repository.BrandRepository
import com.tddrestapi.support.exception.ResourceNotFoundException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class BrandManageServiceTest : StringSpec({

    val brandRepository = mockk<BrandRepository>()

    val brandManageService = BrandManageService(
        brandRepository,
    )

    "브랜드 코드를 통해 브랜드를 정상 조회할 수 있다" {
        // given
        val brand = Brand(id = 1, name = "Nike", code = "NIKE")

        every { brandRepository.findByCodeAndDeletedFalse("NIKE") } returns brand

        // when
        val result = brandManageService.findByCodeOrThrow("NIKE")

        // then
        result shouldBe brand
    }

    "존재하지 않는 브랜드 코드를 조회하면 예외가 발생한다" {
        // given
        every { brandRepository.findByCodeAndDeletedFalse("UNKNOWN") } returns null

        // when & then
        shouldThrow<ResourceNotFoundException> {
            brandManageService.findByCodeOrThrow("UNKNOWN")
        }
    }
})
