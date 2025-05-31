package com.personal.provend.domain

import com.personal.provend.domain.models.Product

interface ProductRepository {

    suspend fun getProducts(): List<Product>

    suspend fun getProduct(id: Int): Product?

    suspend fun update(product: Product): Product

    suspend fun delete(product: Product)
}
