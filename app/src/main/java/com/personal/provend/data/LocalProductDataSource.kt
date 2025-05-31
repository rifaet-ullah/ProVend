package com.personal.provend.data

import com.personal.provend.domain.models.Product

class LocalProductDataSource {

    private val products = mutableListOf<Product>()

    fun add(product: Product) {
        products.add(product)
    }

    fun getProducts(): List<Product> = products.toList()

    fun getProductById(id: Int): Product? = products.find { it.id == id }

    fun update(product: Product): Product {
        val index = products.indexOfFirst { it.id == product.id }
        if (index == -1) throw IllegalArgumentException("Product not found")

        products[index] = product
        return product
    }

    fun delete(product: Product) {
        products.remove(product)
    }
}
