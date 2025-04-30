package com.tddrestapi.application.manage.service

import com.tddrestapi.application.manage.dto.BrandCreateRequest
import com.tddrestapi.application.manage.dto.BrandResponse
import com.tddrestapi.application.manage.dto.BrandUpdateRequest
import com.tddrestapi.domain.brand.entity.Brand
import com.tddrestapi.domain.brand.repository.BrandRepository
import com.tddrestapi.support.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrandManageService(
    private val brandRepository: BrandRepository,
) {
    fun getAllBrands(): List<BrandResponse> =
        brandRepository.findAll()
            .map { brand ->
                BrandResponse(
                    id = brand.id!!,
                    name = brand.name,
                    code = brand.code
                )
            }

    fun findByCodeOrThrow(code: String): Brand =
        brandRepository.findByCodeAndDeletedFalse(code)
            ?: throw ResourceNotFoundException("Brand", "code", code)

    fun findByIdOrThrow(id: Int): Brand =
        brandRepository.findByIdAndDeletedFalse(id) ?: throw ResourceNotFoundException("Brand", "id", id)

    @Transactional
    fun createBrand(request: BrandCreateRequest): Brand {
        val brand = Brand(
            name = request.name,
            code = request.code
        )
        return brandRepository.save(brand)
    }

    @Transactional
    fun updateBrand(id: Int, request: BrandUpdateRequest): Brand {
        val brand = findByIdOrThrow(id)
        request.name?.let { brand.name = it }
        request.code?.let { brand.code = it }
        return brandRepository.save(brand)
    }

    @Transactional
    fun deleteBrand(id: Int) {
        val brand = findByIdOrThrow(id)
        brand.deleted = true
        brandRepository.save(brand)
    }
}
