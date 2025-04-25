package com.tddrestapi.domain.brand.repository

import com.tddrestapi.domain.brand.entity.Brand
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BrandRepository : JpaRepository<Brand, Int> {
    fun existsByNameAndDeletedFalse(name: String): Boolean
    fun findAllByDeletedFalse(): List<Brand>
    fun findByIdAndDeletedFalse(id: Int): Brand?
}
