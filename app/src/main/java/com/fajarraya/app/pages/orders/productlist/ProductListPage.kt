package com.fajarraya.app.pages.orders.productlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fajarraya.app.components.cards.ProductCard
import com.fajarraya.app.components.dropdown.Dropdown
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.models.SortType
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListPage(
    modifier: Modifier = Modifier,
    productListViewModel: ProductListViewModel = koinViewModel(),
    navHostController: NavHostController
) {
    val products = productListViewModel.productList.observeAsState()

    var sortType by remember {
        mutableStateOf(SortType.ASCENDING)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(products, sortType) {
        productListViewModel.getAllProducts(sortType)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp), verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedTextField(
                    modifier = Modifier.width(340.dp),
                    value = TextFieldValue(searchText),
                    shape = RoundedCornerShape(20.dp),
                    onValueChange = {
                        searchText = it.text
                        productListViewModel.searchProducts(searchText)
                    },
                    placeholder = {
                        Text(
                            text = "Search For Products",
                            fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
                        )
                    },
                )

                Dropdown {
                    DropdownMenuItem(text = {
                        Text(
                            text = "Sort by Descending ( A - Z )"
                        )
                    }, onClick = {
                        sortType = SortType.DESCENDING
                        productListViewModel.getAllProducts(sortType)
                    })

                    DropdownMenuItem(text = { Text(
                        text = "Sort by Ascending ( Z - A )"
                    )}, onClick = {
                        sortType = SortType.ASCENDING
                        productListViewModel.getAllProducts(sortType)
                    })
                }

            }

        }

        if (products.value != null) {
            items(products.value!!) {
                ProductCard(
                    productName = it.namaBarang,
                    imageUrl = it.gambarProduk,
                    stokLeft = it.stok
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            navHostController.navigate(
                                Screen.Orders.EditProduct.route + "/${it.kodeBarang}"
                            )
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "add-button")
                        }
                        IconButton(onClick = {
                            productListViewModel.deleteProduct(it) {
                                navHostController.popBackStack()
                            }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "delete-button")
                        }

                    }
                }
            }
        }
    }
}