package com.personal.provend.data

import com.personal.provend.domain.models.Product
import com.personal.provend.domain.models.ProductCategory
import kotlinx.coroutines.delay

class RemoteProductDataSource {

    private val products = mutableListOf(
        Product(
            id = 1,
            name = "Orange Juice",
            price = 40.0,
            category = ProductCategory.SOFT_DRINKS,
            countInStock = 10,
        ),
        Product(
            id = 2,
            name = "Apple Juice",
            price = 40.0,
            category = ProductCategory.JUICES,
            countInStock = 10,
        ),
        Product(
            id = 3,
            name = "Orange Juice",
            price = 40.0,
            category = ProductCategory.JUICES,
            countInStock = 10,
        ),
        Product(
            id = 4,
            name = "Orange Juice",
            price = 40.0,
            category = ProductCategory.JUICES,
            countInStock = 10,
        ),
    )

    suspend fun getProducts(): List<Product> {
        delay(2_000)
        return products
    }

    fun update(product: Product) {
        val index = products.indexOfFirst { it.id == product.id }
        if (index == -1) throw IllegalArgumentException("Product not found")
    }

    fun delete(product: Product) {
        products.remove(product)
    }
}