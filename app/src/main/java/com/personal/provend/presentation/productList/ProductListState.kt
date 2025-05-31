package com.personal.provend.presentation.productList

import androidx.compose.ui.text.input.TextFieldValue
import com.personal.provend.presentation.moldels.UiProduct

sealed interface ProductListState {

    data object Loading : ProductListState

    data class View(
        val query: TextFieldValue,
        val selectedCategoryIndex: Int = -1,
        val products: List<UiProduct>,
    ) : ProductListState

    data object Error : ProductListState
}
