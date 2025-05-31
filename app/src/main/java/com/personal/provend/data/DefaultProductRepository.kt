package com.personal.provend.data

import com.personal.provend.domain.ProductRepository
import com.personal.provend.domain.models.Product

class DefaultProductRepository(
    private val cacheTime: Long = 120_000,
    private val localProductDataSource: LocalProductDataSource,
    private val remoteProductDataSource: RemoteProductDataSource,
) : ProductRepository {

    private var lastTimeFetched = 0L

    override suspend fun getProducts(): List<Product> {
        if (System.currentTimeMillis() - lastTimeFetched < cacheTime) {
            return localProductDataSource.getProducts()
        }

        val products = remoteProductDataSource.getProducts()
        products.forEach {
            try {
                localProductDataSource.update(it)
            } catch (_: IllegalArgumentException) {
                localProductDataSource.add(it)
            }
        }
        lastTimeFetched = System.currentTimeMillis()
        return products
    }

    override suspend fun getProduct(id: Int): Product? = localProductDataSource.getProductById(id)

    override suspend fun update(product: Product): Product {
        remoteProductDataSource.update(product)
        localProductDataSource.update(product)

        return product
    }

    override suspend fun delete(product: Product) {
        remoteProductDataSource.delete(product)
        localProductDataSource.delete(product)
    }
}
