package suda.com.herramientasintellij.repository

import org.springframework.data.jpa.repository.JpaRepository
import suda.com.herramientasintellij.model.Product

interface ProductRepository : JpaRepository<Product, Long>