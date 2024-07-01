package com.fajarraya.app.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fajarraya.app.R
import com.fajarraya.app.components.buttons.PrimaryButton
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.core.domain.model.Transactions
import com.fajarraya.app.ui.theme.PrimaryBlue
import com.fajarraya.app.utils.Extensions

@Composable
fun TransactionCard(
    modifier : Modifier = Modifier,
    transaction: com.fajarraya.app.models.Transactions,
){
    Card(
        modifier = modifier
            .fillMaxWidth(),

        border = BorderStroke(1.dp, PrimaryBlue),
        colors = CardDefaults.cardColors(contentColor = Color.Black, containerColor = Color.White)
    ) {

        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){

            Column(
                modifier=Modifier
                    .weight(1f)
            ){
                transaction.items.forEach{
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {

                        AsyncImage(
                            model = it.gambar, contentDescription = "product-image",
                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(70.dp)
                                .width(70.dp),
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                            Text(
                                text = it.nama,
                                fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_SEMI),
                                fontSize = WidgetConstants.SUBHEADER_FONT_SIZE.sp,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = "${it.quantity} x ${Extensions.toRupiah(it.harga)}",
                                fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }




                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(5.dp), horizontalAlignment = Alignment.CenterHorizontally ) {
                Text(
                    text = if(transaction.status == "Completed") stringResource(id = R.string.completed) else stringResource(
                        id = R.string.in_progress
                    ),
                    color = if(transaction.status == "Completed") Color.Green else Color.Yellow,
                    fontSize = WidgetConstants.PARAGRAPH_FONT_SIZE.sp,
                    textAlign = TextAlign.Center
                )

                PrimaryButton(
                    onClick = {},
                    buttonText = stringResource(id = R.string.details),
                    modifier = Modifier.width(120.dp).height(50.dp)
                )

            }

        }


    }
}