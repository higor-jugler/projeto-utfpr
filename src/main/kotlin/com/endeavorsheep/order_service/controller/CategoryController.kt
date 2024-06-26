package com.endeavorsheep.order_service.controller

import com.endeavorsheep.order_service.entity.Category
import com.endeavorsheep.order_service.repository.RepositoryCategory
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import kotlin.jvm.optionals.getOrNull

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val repositoryCategory: RepositoryCategory
) {

    @GetMapping
    fun list(@RequestParam name: String?): List<Category> {
        name?.let {
            return repositoryCategory.findByNameContainsIgnoreCase(it)
        }
        return repositoryCategory.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Category> {
        val category = repositoryCategory.findById(id).getOrNull() ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(category)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        repositoryCategory.findById(id).getOrNull()?.let {
            repositoryCategory.delete(it)
        }
        return ResponseEntity.noContent().build()
    }

    @PostMapping
    fun save(@Valid @RequestBody category: Category): Category {
        return repositoryCategory.save(category)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, String> {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage ?: ""
            errors[fieldName] = errorMessage
        }
        return errors
    }

}