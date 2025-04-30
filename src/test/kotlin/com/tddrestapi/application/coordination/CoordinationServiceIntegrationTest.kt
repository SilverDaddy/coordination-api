package com.tddrestapi.application.coordination

import com.tddrestapi.application.coordination.service.CoordinationService
import com.tddrestapi.domain.brand.entity.Brand
import com.tddrestapi.domain.brand.repository.BrandRepository
import com.tddrestapi.domain.category.entity.Category
import com.tddrestapi.domain.category.repository.CategoryRepository
import com.tddrestapi.domain.product.entity.Product
import com.tddrestapi.domain.product.repository.ProductRepository
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@DisplayName("CoordinationService 통합 테스트")
class CoordinationServiceIntegrationTest : StringSpec() {

    override fun extensions() = listOf(SpringExtension)

    @Autowired
    private lateinit var coordinationService: CoordinationService

    @Autowired
    private lateinit var brandRepository: BrandRepository

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    init {
        beforeSpec {
            // 데이터 정리
            productRepository.deleteAll()
            brandRepository.deleteAll()
            categoryRepository.deleteAll()

            // 카테고리
            val top = categoryRepository.save(Category(label = "상의", code = "TOP"))
            val bottom = categoryRepository.save(Category(label = "바지", code = "BOTTOM"))

            // 브랜드
            val nike = brandRepository.save(Brand(name = "Nike", code = "NIKE"))
            val adidas = brandRepository.save(Brand(name = "Adidas", code = "ADIDAS"))

            // 상품 등록
            productRepository.save(
                Product(
                    name = "나이키 티셔츠",
                    modelNumber = "N-TOP-001",
                    price = 10000,
                    brand = nike,
                    category = top
                )
            )

            productRepository.save(
                Product(
                    name = "아디다스 바지",
                    modelNumber = "A-BOTTOM-001",
                    price = 12000,
                    brand = adidas,
                    category = bottom
                )
            )
        }

        "카테고리별 최저 가격 상품 요약 조회" {
            val result = coordinationService.getCategoryCheapestSummary()
            result.cheapestTotalPrice shouldBe 22000
            result.categorySummaries shouldHaveSize 2
        }

        "단일 브랜드 최저 총액 조회 실패 (브랜드가 모든 카테고리를 커버하지 않음)" {
            shouldThrowAny {
                coordinationService.getCheapestBrandForAllCategories()
            }
        }

        "특정 카테고리 기준 최저 최고 가격 조회" {
            val result = coordinationService.getExtremeBrandPricesByCategory("상의")
            result.category shouldBe "상의"
            result.lowest.first().brand shouldBe "Nike"
            result.highest.first().brand shouldBe "Nike"
        }
    }
}
