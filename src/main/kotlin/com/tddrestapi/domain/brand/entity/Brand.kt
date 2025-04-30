package com.tddrestapi.domain.brand.entity

import com.tddrestapi.domain.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class Brand(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(unique = true, nullable = false)
    var code: String,

    @Column(unique = true)
    var name: String,

    @Column
    var deleted: Boolean = false
) : BaseEntity()
