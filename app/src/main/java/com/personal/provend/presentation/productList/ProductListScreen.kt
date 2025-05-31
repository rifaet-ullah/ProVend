package com.personal.provend.presentation.productList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.personal.provend.R
import com.personal.provend.presentation.moldels.DisplayPrice
import com.personal.provend.presentation.moldels.UiProduct
import com.personal.provend.ui.theme.ProVendTheme

@Composable
fun ProductListScreen() {
    Content(uiState = ProductListState.Loading)
}

@Composable
private fun Content(uiState: ProductListState) {
    Scaffold { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            when (uiState) {
                ProductListState.Loading -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }

                ProductListState.Error -> {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = stringResource(R.string.product_loading_error_message),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(0.6f)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(onClick = {}) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.Refresh,
                                    contentDescription = stringResource(R.string.retry)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = stringResource(R.string.retry))
                            }
                        }
                    }
                }

                is ProductListState.View -> {
                    val categories = uiState.products.map { it.category }.distinct()

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        OutlinedTextField(
                            value = uiState.query,
                            onValueChange = {},
                            maxLines = 1,
                            shape = RoundedCornerShape(50),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = stringResource(R.string.search),
                                )
                            },
                            placeholder = {
                                Text(text = stringResource(R.string.search))
                            },
                            trailingIcon = if (uiState.query.text.isNotEmpty()) {
                                {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            imageVector = Icons.Outlined.Clear,
                                            contentDescription = stringResource(R.string.clear)
                                        )
                                    }
                                }
                            } else null,
                            modifier = Modifier.fillMaxWidth()
                        )

                        AnimatedVisibility(visible = categories.isNotEmpty()) {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp)
                            ) {
                                itemsIndexed(categories) { index, category ->
                                    OutlinedButton(
                                        onClick = {},
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    ) {
                                        Text(text = category)
                                        if (index == uiState.selectedCategoryIndex) {
                                            Icon(
                                                imageVector = Icons.Outlined.Clear,
                                                contentDescription = stringResource(R.string.clear)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.weight(1f)
                        ) {
                            items(uiState.products) { product ->
                                Card(modifier = Modifier.padding(8.dp)) {
                                    Image(
                                        painter = painterResource(product.image),
                                        contentDescription = product.name
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private val uiProducts = listOf(
    UiProduct(
        id = 1,
        name = "Orange Juice",
        price = DisplayPrice(
            value = 40.0, symbol = "$"
        ),
        category = "Juice",
        countInStock = 10,
        image = R.drawable.ic_launcher_foreground
    ),
    UiProduct(
        id = 1,
        name = "Orange Juice",
        price = DisplayPrice(
            value = 40.0, symbol = "$"
        ),
        category = "Energy Drinks",
        countInStock = 10,
        image = R.drawable.ic_launcher_foreground
    ),
    UiProduct(
        id = 1,
        name = "Orange Juice",
        price = DisplayPrice(
            value = 40.0, symbol = "$"
        ),
        category = "Water",
        countInStock = 10,
        image = R.drawable.ic_launcher_foreground
    ),
    UiProduct(
        id = 1,
        name = "Orange Juice",
        price = DisplayPrice(
            value = 40.0, symbol = "$"
        ),
        category = "Soft Drinks",
        countInStock = 10,
        image = R.drawable.ic_launcher_foreground
    ),
)

@PreviewLightDark
@Composable
private fun LoadingPreview() {
    ProVendTheme {
        Content(uiState = ProductListState.Loading)
    }
}

@PreviewLightDark
@Composable
private fun LoadingSuccessPreview() {
    ProVendTheme {
        Content(
            uiState = ProductListState.View(
                query = TextFieldValue(""),
                products = uiProducts
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun ErrorPreview() {
    ProVendTheme {
        Content(uiState = ProductListState.Error)
    }
}
