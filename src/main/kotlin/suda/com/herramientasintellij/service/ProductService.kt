package suda.com.herramientasintellij.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import suda.com.herramientasintellij.model.Product
import suda.com.herramientasintellij.repository.ProductRepository

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun saludoService(): String = "Hola desde ProductService"

    fun listar(): List<Product> = productRepository.findAll()

    fun buscarPorId(id: Long): Product? = productRepository.findByIdOrNull(id)

    fun crear(product: Product): Product {
        product.id = null
        return productRepository.save(product)
    }

    fun actualizar(id: Long, datos: Product): Product? {
        val existente = buscarPorId(id) ?: return null
        existente.description = datos.description
        existente.price = datos.price
        existente.stock = datos.stock
        return productRepository.save(existente)
    }

    fun eliminar(id: Long): Boolean {
        if (!productRepository.existsById(id)) return false
        productRepository.deleteById(id)
        return true
    }
}