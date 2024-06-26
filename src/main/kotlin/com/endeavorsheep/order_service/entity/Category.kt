package com.endeavorsheep.order_service.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank

@Entity
class Category (
    @Id
    @GeneratedValue
    var id: Int = 0,
    @field:NotBlank(message = "{name.notBlank}")
    var name: String = ""
)