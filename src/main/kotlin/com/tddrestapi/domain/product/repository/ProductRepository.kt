package com.tddrestapi.domain.product.repository

import com.tddrestapi.domain.brand.entity.Brand
import com.tddrestapi.domain.category.entity.Category
import com.tddrestapi.domain.product.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Int> {
    fun findAllByCategory(category: Category): List<Product>
    fun countByBrandAndCategory(brand: Brand, category: Category): Long
}
