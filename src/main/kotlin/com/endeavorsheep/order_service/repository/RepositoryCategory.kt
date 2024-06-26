package com.endeavorsheep.order_service.repository

import com.endeavorsheep.order_service.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface RepositoryCategory: JpaRepository<Category, Int> {
    fun findByNameContainsIgnoreCase(name: String): List<Category>
}