package com.tddrestapi.domain.category.repository

import com.tddrestapi.domain.category.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Int> {
    fun findByName(name: String): Category?
    fun existsByName(name: String): Boolean
}
