package com.tddrestapi.application.manage.service

import com.tddrestapi.application.manage.dto.CategoryCreateRequest
import com.tddrestapi.application.manage.dto.CategoryResponse
import com.tddrestapi.application.manage.dto.CategoryUpdateRequest
import com.tddrestapi.domain.category.entity.Category
import com.tddrestapi.domain.category.repository.CategoryRepository
import com.tddrestapi.support.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryManageService(
    private val categoryRepository: CategoryRepository
) {
    fun getAllCategories(): List<CategoryResponse> =
        categoryRepository.findAll()
            .map { category ->
                CategoryResponse(
                    id = category.id!!,
                    label = category.label,
                    code = category.code
                )
            }

    fun findByCodeOrThrow(code: String): Category =
        categoryRepository.findByCodeAndDeletedFalse(code)
            ?: throw ResourceNotFoundException("Category", "code", code)

    fun findByIdOrThrow(id: Int): Category =
        categoryRepository.findByIdAndDeletedFalse(id) ?: throw ResourceNotFoundException("Category", "id", id)

    @Transactional
    fun createCategory(request: CategoryCreateRequest): Category {
        val category = Category(
            label = request.label,
            code = request.code
        )
        return categoryRepository.save(category)
    }

    @Transactional
    fun updateCategory(id: Int, request: CategoryUpdateRequest): Category {
        val category = findByIdOrThrow(id)
        request.label?.let { category.label = it }
        request.code?.let { category.code = it }
        return categoryRepository.save(category)
    }

    @Transactional
    fun deleteCategory(id: Int) {
        val category = findByIdOrThrow(id)
        category.deleted = true
        categoryRepository.save(category)
    }
}
