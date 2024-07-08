package com.fajarraya.app.pages.transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fajarraya.app.components.cards.TransactionCard
import com.fajarraya.app.components.dropdown.Dropdown
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.models.SortType
import org.koin.androidx.compose.koinViewModel

@Composable
fun TransactionsPage(
    modifier: Modifier = Modifier,
    transactionsViewModel: TransactionsViewModel = koinViewModel(),
    navHostController: NavHostController
) {

    var sortType by remember {
        mutableStateOf(SortType.ASCENDING)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    val transactions = transactionsViewModel.transactions.observeAsState()

    LaunchedEffect(transactions,sortType) {
        transactionsViewModel.subscribeTransactions(sortType)
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
                        transactionsViewModel.searchTransactions(searchText)
                    },
                    placeholder = {
                        Text(
                            text = "Search For Transactions",
                            fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp
                        )
                    },
                )

                Dropdown {
                    DropdownMenuItem(text = {
                        Text(
                            text = "Sort by Newest Added"
                        )
                    }, onClick = {
                        sortType = SortType.DESCENDING
                        transactionsViewModel.subscribeTransactions(sortType)
                    })

                    DropdownMenuItem(text = { Text(
                        text = "Sort by Oldest Added"
                    )
                    }, onClick = {
                        sortType = SortType.ASCENDING
                        transactionsViewModel.subscribeTransactions(sortType)
                    })
                }

            }
        }


        if(transactions.value !== null){
            items(transactions.value!!) {
                TransactionCard(
                    transaction = it,
                    username = transactionsViewModel.authUseCase.userDataByID(it.userid),
                    onDetailClick = {
                        navHostController.navigate(Screen.Orders.PrintReceiptPage.route + "/${it.transID}")
                    }
                )
            }
        }


    }


}