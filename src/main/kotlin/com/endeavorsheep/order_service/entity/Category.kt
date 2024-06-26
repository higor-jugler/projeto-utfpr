package com.endeavorsheep.order_service.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Category (
    @Id
    @GeneratedValue
    var id: Int = 0,
    var name: String = ""
)