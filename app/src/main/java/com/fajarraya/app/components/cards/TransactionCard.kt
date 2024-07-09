package com.fajarraya.app.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fajarraya.app.R
import com.fajarraya.app.components.buttons.PrimaryButton
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.core.domain.model.User
import com.fajarraya.app.models.Transactions
import com.fajarraya.app.ui.theme.PrimaryBlue
import com.fajarraya.app.utils.Extensions
import io.reactivex.rxjava3.core.Single
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun TransactionCard(
    modifier: Modifier = Modifier,
    transaction: Transactions,
    onDetailClick: () -> Unit = {},
    username: Single<User>
) {
    val uname = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = Unit) {
        username
            .subscribe({
                uname.value = it.username
            }, {
                uname.value = ""
            })
    }

    val date = SimpleDateFormat("dd MMMM yyyy").format(Date(transaction.date))

    Card(
        modifier = modifier
            .fillMaxWidth(),
        border = BorderStroke(1.dp, PrimaryBlue),
        colors = CardDefaults.cardColors(contentColor = Color.Black, containerColor = Color.White)
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Text(
                    text = "Tanggal Transaksi : ${date}",
                    fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp,top=10.dp),
                    )

                Text(
                    text = "Pengguna : ${uname.value}",
                    fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp,top=0.dp),
                )

                Text(
                    text = "Daftar Produk : ",
                    fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp,top=0.dp),
                )

                transaction.items.forEach {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {

                        AsyncImage(
                            model = it.gambar, contentDescription = "product-image",
                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(60.dp)
                                .width(60.dp),
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                            Text(
                                text = it.nama,
                                fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
                                fontSize = WidgetConstants.SUBHEADER_FONT_SIZE.sp,
                            )

                            Text(
                                text = "${it.quantity} x ${Extensions.toRupiah(it.harga)}",
                                fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp,
                            )
                        }
                    }
                }


            }


            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (transaction.status == "Completed") stringResource(id = R.string.completed) else stringResource(
                        id = R.string.in_progress
                    ),
                    color = if (transaction.status == "Completed") Color.Green else Color.Yellow,
                    fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp,
                    textAlign = TextAlign.Center
                )

                PrimaryButton(
                    onClick = onDetailClick,
                    buttonText = stringResource(id = R.string.details),
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                )

            }

        }


    }
}