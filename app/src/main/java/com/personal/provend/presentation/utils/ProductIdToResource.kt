package com.personal.provend.presentation.utils

import com.personal.provend.R

fun getDrawableIdForProduct(productId: Int) = when (productId) {
    0 -> R.drawable.ic_launcher_foreground
    else -> R.drawable.ic_launcher_background
}
