package com.fajarraya.app.pages.printreceipt

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.utils.Extensions
import org.koin.androidx.compose.koinViewModel

@Composable
fun PrintReceiptPage(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    printReceiptViewModel: PrintReceiptViewModel = koinViewModel(),
    transactionId: String?
) {

    val transaction = printReceiptViewModel.transaction.observeAsState()

    val totalitem = printReceiptViewModel.totalitem.observeAsState()
    val totalhargawotax = printReceiptViewModel.totalpricewotax.observeAsState()
    val tax = printReceiptViewModel.tax.observeAsState()
    val totalharga = printReceiptViewModel.totalprice.observeAsState()

    val payment = printReceiptViewModel.payment.observeAsState()


    LaunchedEffect(key1 = Unit) {
        if (transactionId != null) {
            printReceiptViewModel.loadItemData(transactionId)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 0.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Preview",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFDBDBDB))
                    .border(1.dp, Color.Black)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "FAJAR RAYA\nMEDAN",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF468BF2),
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Transaction ID : ${transactionId}",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))

                if (transaction.value != null) {
                    for (item in transaction.value!!.items) {
                        Text(
                            text = item.nama,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "${item.quantity} x ${Extensions.toRupiah(item.harga)}")
                            Text(text = Extensions.toRupiah(item.quantity * item.harga))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                HorizontalDivider(color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total Harga")
                    Text(text = totalhargawotax.value?.let { Extensions.toRupiah(it) }
                        ?: "Loading...")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Pajak (10%)")
                    Text(text = tax.value?.let { Extensions.toRupiah(it) } ?: "Loading...")
                }
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFADADAD)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Quantity : ${totalitem.value}")
                    Text(text = "Paid with : ${payment.value}")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFADADAD)),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        text = "Total : ${totalharga.value?.let { Extensions.toRupiah(it) } ?: "Loading..."}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Thank You",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )
                HorizontalDivider(color = Color.Black)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(
            onClick = {
                navHostController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Orders.PrintReceiptPage.route + "/$transactionId") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(text = "Back to Main Menu")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(text = "Export to PDF")
        }
    }


}