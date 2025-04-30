package com.tddrestapi.application.manage.service

import com.tddrestapi.application.manage.dto.ProductCreateRequest
import com.tddrestapi.application.manage.dto.ProductResponse
import com.tddrestapi.application.manage.dto.ProductUpdateRequest
import com.tddrestapi.domain.product.entity.Product
import com.tddrestapi.domain.product.repository.ProductRepository
import com.tddrestapi.support.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductManageService(
    private val productRepository: ProductRepository,
    private val brandManageService: BrandManageService,
    private val categoryManageService: CategoryManageService,
) {
    fun getAllProducts(): List<ProductResponse> {
        return productRepository.findAllWithRelations()
            .map { product -> ProductResponse.from(product) }
    }

    fun findByIdOrThrow(id: Int): Product =
        productRepository.findByIdAndDeletedFalse(id) ?: throw ResourceNotFoundException("Product", "id", id)

    @Transactional
    fun createProduct(request: ProductCreateRequest): Product {
        val brand = brandManageService.findByCodeOrThrow(request.brandCode)
        val category = categoryManageService.findByCodeOrThrow(request.categoryCode)

        val nextSequence = productRepository.countByBrandAndCategory(brand, category) + 1
        val modelNumber = generateModelNumber(brand.code, category.code, nextSequence)

        val product = Product(
            name = request.name,
            modelNumber = modelNumber,
            brand = brand,
            category = category,
            price = request.price
        )

        return productRepository.save(product)
    }

    @Transactional
    fun updateProduct(id: Int, request: ProductUpdateRequest): Product {
        val product = findByIdOrThrow(id)

        var brand = product.brand
        var category = product.category
        var shouldRegenerateModelNumber = false

        request.brandCode?.let { code ->
            brand = brandManageService.findByCodeOrThrow(code)
            product.brand = brand
            shouldRegenerateModelNumber = true
        }

        request.categoryCode?.let { code ->
            category = categoryManageService.findByCodeOrThrow(code)
            product.category = category
            shouldRegenerateModelNumber = true
        }

        request.name?.let { product.name = it }
        request.price?.let { product.price = it }

        if (shouldRegenerateModelNumber) {
            val nextSequence = productRepository.countByBrandAndCategory(brand, category) + 1
            product.modelNumber = generateModelNumber(brand.code, category.code, nextSequence)
        }

        return productRepository.save(product)
    }

    @Transactional
    fun deleteProduct(id: Int) {
        val product = findByIdOrThrow(id)
        product.deleted = true
        product.softDelete()
        productRepository.save(product)
    }

    private fun generateModelNumber(brandCode: String, categoryCode: String, sequence: Int): String {
        return "$brandCode-$categoryCode-${sequence.toString().padStart(3, '0')}"
    }
}
