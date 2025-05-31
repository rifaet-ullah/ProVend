package com.personal.provend.presentation.moldels

import android.app.Application
import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.personal.provend.domain.models.Product
import com.personal.provend.presentation.utils.getDrawableIdForProduct
import java.util.Locale

data class UiProduct(
    val id: Int,
    val name: String,
    val price: DisplayPrice,
    val category: String,
    val countInStock: Int,
    @DrawableRes val image: Int,
)

data class DisplayPrice(
    val value: Double,
    val symbol: String,
) {
    val formatted: String
        get() {
            val numberFormatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
                minimumFractionDigits = 2
                maximumFractionDigits = 2
            }
            return "$symbol ${numberFormatter.format(value)}"
        }
}

fun Product.toUiProduct(application: Application) = UiProduct(
    id = id,
    name = name,
    price = DisplayPrice(price, "$"),
    image = getDrawableIdForProduct(id),
    category = category.name.first() + category.name.substring(1).replace('_', ' ').lowercase(),
    countInStock = countInStock,
)
