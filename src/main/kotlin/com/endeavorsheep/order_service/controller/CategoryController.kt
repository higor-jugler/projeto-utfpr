package com.endeavorsheep.order_service.controller

import com.endeavorsheep.order_service.entity.Category
import com.endeavorsheep.order_service.repository.RepositoryCategory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val repositoryCategory: RepositoryCategory
) {

    @GetMapping
    fun list(@RequestParam name: String?): List<Category>{
        name?.let {
            return repositoryCategory.findByNameContainsIgnoreCase(it)
        }
        return repositoryCategory.findAll()
    }
}