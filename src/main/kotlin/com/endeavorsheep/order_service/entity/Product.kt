package com.endeavorsheep.order_service.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Entity
@Table(name = "my-product")
class Product(

    @Id
    @GeneratedValue
    var id: Int,

    @field:NotBlank(message = "{name.notBlank}")
    @field:Size(max = 100, message = "{name.size}")
    @Column(name = "name_product", nullable = false, length = 100)
    var name: String = "",

    @field:Positive(message = "{price.positive}")
    @Column(name = "sale_value", nullable = false, precision = 15, scale = 2)
    var price: BigDecimal = BigDecimal.ZERO,

    @field:NotNull(message = "{category.notOrNull}")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_category")
    var category: Category? = null
)