package suda.com.herramientasintellij.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import suda.com.herramientasintellij.model.Product
import suda.com.herramientasintellij.service.ProductService
import java.net.URI

@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService) {

    @GetMapping("/hola")
    fun saludo(): String = "Hola mundo"

    @GetMapping("/adios")
    fun despedida(): String = "Adios mundo"

    @GetMapping("/saludo-service")
    fun saludoService(): String = productService.saludoService()

    @GetMapping
    fun listar(): List<Product> = productService.listar()

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Product> =
        productService.buscarPorId(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PostMapping
    fun crear(@Valid @RequestBody product: Product): ResponseEntity<Product> {
        val creado = productService.crear(product)
        return ResponseEntity.created(URI("/api/products/${creado.id}")).body(creado)
    }

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Long, @Valid @RequestBody product: Product): ResponseEntity<Product> =
        productService.actualizar(id, product)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Long): ResponseEntity<Void> =
        if (productService.eliminar(id)) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()
}