package com.endeavorsheep.order_service.repository

import com.endeavorsheep.order_service.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RepositoryProduct : JpaRepository<Product, Int> {
    @Query(
        """
        SELECT p FROM Product p
        WHERE CAST(p.id AS String) LIKE CONCAT('%', :query, '%')
            OR UPPER(p.name) LIKE UPPER(CONCAT('%', :query, '%'))
            OR CAST(p.price AS String) LIKE CONCAT('%', :query, '%')
            OR UPPER(p.category.name) LIKE UPPER(CONCAT('%', :query, '%'))
    """
    )
    fun findByQuery(@Param("query") query: String): List<Product>
}