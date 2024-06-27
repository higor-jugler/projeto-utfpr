package com.endeavorsheep.order_service.controller

import com.endeavorsheep.order_service.entity.Product
import com.endeavorsheep.order_service.repository.RepositoryProduct
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

@RestController
@RequestMapping("/products")
class ControllerProduct(
    private val repositoryProduct: RepositoryProduct
) {

    @GetMapping
    fun list(@RequestParam query: String): List<Product> {
        return repositoryProduct.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable(name = "id") code: Int): ResponseEntity<Product> {
        val product = repositoryProduct.findById(code).getOrNull()
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(product)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        repositoryProduct.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping
    fun save(@RequestBody product: Product): Product {
        return repositoryProduct.save(product)
    }
}