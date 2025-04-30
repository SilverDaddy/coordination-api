package com.tddrestapi.domain.product.repository

import com.tddrestapi.domain.brand.entity.Brand
import com.tddrestapi.domain.category.entity.Category
import com.tddrestapi.domain.product.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Int> {

    fun findByIdAndDeletedFalse(id: Int): Product?

    @Query(
        """
    SELECT p FROM Product p
    JOIN FETCH p.brand
    JOIN FETCH p.category
    WHERE p.deleted = false
"""
    )
    fun findAllWithRelations(): List<Product>

    fun countByBrandAndCategory(brand: Brand, category: Category): Int

    @Query(
        """
    SELECT p FROM Product p
    WHERE p.deleted = false
      AND p.price = (
            SELECT MIN(p2.price)
            FROM Product p2
            WHERE p2.category = p.category AND p2.deleted = false
      )
    ORDER BY p.category.id, p.id
"""
    )
    fun findAllLowestPriceProductsGroupedByCategory(): List<Product>

    /* 브랜드-카테고리별 최저가 상품 리스트 */
    @Query(
        """
    SELECT p FROM Product p
    WHERE p.deleted = false
      AND p.price = (
        SELECT MIN(p2.price)
        FROM Product p2
        WHERE p2.category = p.category AND p2.brand = p.brand AND p2.deleted = false
      )
"""
    )
    fun findMinPriceProductsGroupedByBrandAndCategory(): List<Product>

    /** 특정 카테고리에서 최저 가격 상품 리스트 */
    @Query(
        """
        SELECT p FROM Product p
        WHERE p.category = :category
          AND p.deleted = false
          AND p.price = (
              SELECT MIN(p2.price) 
              FROM Product p2 
              WHERE p2.category = :category AND p2.deleted = false
          )
    """
    )
    fun findLowestPriceProductsByCategory(@Param("category") category: Category): List<Product>

    /** 특정 카테고리에서 최고 가격 상품 리스트 */
    @Query(
        """
        SELECT p FROM Product p
        WHERE p.category = :category
          AND p.deleted = false
          AND p.price = (
              SELECT MAX(p2.price) 
              FROM Product p2 
              WHERE p2.category = :category AND p2.deleted = false
          )
    """
    )
    fun findHighestPriceProductsByCategory(@Param("category") category: Category): List<Product>
}
