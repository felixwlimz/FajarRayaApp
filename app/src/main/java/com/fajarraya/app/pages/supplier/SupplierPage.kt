package com.fajarraya.app.pages.supplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.fajarraya.app.components.cards.SupplierCard
import com.fajarraya.app.components.dropdown.Dropdown
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.models.SortType
import com.fajarraya.app.models.UserType
import org.koin.androidx.compose.koinViewModel

@Composable
fun SupplierPage(
    modifier: Modifier = Modifier,
    supplierViewModel: SupplierViewModel = koinViewModel(),
    navHostController: NavHostController
) {

    val suppliers = supplierViewModel.suppliers.observeAsState()
    val currentUser = supplierViewModel.userdata

    var searchText by remember {
        mutableStateOf("")
    }

    var sortType by remember {
        mutableStateOf(SortType.ASCENDING)
    }

    LaunchedEffect(suppliers, sortType, currentUser) {
        supplierViewModel.getAllSuppliers(sortType)
        supplierViewModel.getUser()
    }


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                OutlinedTextField(
                    modifier = Modifier.width(340.dp),
                    value = TextFieldValue(searchText),
                    shape = RoundedCornerShape(20.dp),
                    onValueChange = {
                        searchText = it.text
                        supplierViewModel.searchSupplier(searchText)
                    },
                    placeholder = {
                        Text(
                            text = "Search For Suppliers",
                            fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
                        )
                    },
                )

                Dropdown {
                    DropdownMenuItem(text = {
                        Text(
                            text = "Sort Descending"
                        )
                    }, onClick = {
                        sortType = SortType.DESCENDING
                        supplierViewModel.getAllSuppliers(sortType)

                    })

                    DropdownMenuItem(text = {
                        Text(
                        text = "Sort Ascending"
                        )
                    }, onClick = {
                        sortType = SortType.ASCENDING
                        supplierViewModel.getAllSuppliers(sortType)
                    })
                }

            }
        }

       if(suppliers.value != null){
           items(items = suppliers.value!!) {
               SupplierCard(
                   supplierName = it.supplierName,
                   supplierAddress = it.supplierAddress,
                   phone = it.phoneNumber,
                   city = it.city,
                   province = it.province,
                   buttonContent = {
                      if(currentUser.superAdmin == UserType.OWNER || currentUser.superAdmin == UserType.ADMIN){
                          Row(
                              horizontalArrangement = Arrangement.spacedBy(5.dp),
                              verticalAlignment = Alignment.CenterVertically
                          ) {
                              IconButton(onClick = { navHostController.navigate("${Screen.Supplier.EditSupplier.route}/{id}")}) {
                                  Icon(Icons.Default.Edit, contentDescription = "edit-button")
                              }
                              IconButton(onClick = { supplierViewModel.deleteSupplier(it)}) {
                                  Icon(Icons.Default.Delete, contentDescription = "delete-button")
                              }

                          }
                      } else {
                          Box(modifier = modifier)
                      }
                   }
               )

           }
       }


    }


}