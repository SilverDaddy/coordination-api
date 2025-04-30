package com.tddrestapi.domain.product.entity

import com.tddrestapi.domain.brand.entity.Brand
import com.tddrestapi.domain.category.entity.Category
import com.tddrestapi.domain.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false)
    var name: String,

    @Column(unique = true, nullable = false)
    var modelNumber: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    var brand: Brand,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: Category,

    var price: Int,

    @Column
    var deleted: Boolean = false
) : BaseEntity()
