package com.tddrestapi.domain.category.entity

import com.tddrestapi.domain.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(unique = true, nullable = false)
    var name: String,

    @Column
    var deleted: Boolean = false
) : BaseEntity()
