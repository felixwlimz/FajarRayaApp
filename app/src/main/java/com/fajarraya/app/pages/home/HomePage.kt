package com.fajarraya.app.pages.home

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fajarraya.app.components.cards.StatsCard
import com.fajarraya.app.R
import com.fajarraya.app.components.charts.Cartesian
import com.fajarraya.app.constants.WidgetConstants
import com.fajarraya.app.models.Stats
import com.fajarraya.app.ui.theme.Blue500
import com.fajarraya.app.ui.theme.Blue600
import com.fajarraya.app.ui.theme.PrimaryBlue
import com.fajarraya.app.ui.theme.SecondaryBlue
import com.fajarraya.app.utils.Extensions
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomePage(modifier: Modifier = Modifier, homeViewModel: HomeViewModel = koinViewModel()) {
    val modelProducer = remember {
        CartesianChartModelProducer.build()
    }

    val jumlahProduk by remember {
        derivedStateOf { homeViewModel.productSize }
    }

    val outOfStock by remember {
        derivedStateOf { homeViewModel.outOfStock }
    }

    val totalSales by remember {
        derivedStateOf { homeViewModel.salesSize }
    }

    val revenue by remember {
        derivedStateOf { homeViewModel.salesTotal }
    }


    LaunchedEffect(Unit) {
        homeViewModel.listenProductUpdates();
        homeViewModel.listenTransactionUpdates();

        modelProducer.tryRunTransaction { lineSeries { series(0, 5, 10, 15, 20) } }
    }


    val statsData = listOf(
        Stats(
            statsText = "Products",
            total = jumlahProduk.toLong(),
            icons = R.drawable.baseline_inbox_24,
            color = Blue600
        ),
        Stats(
            statsText = "Revenue",
            total = revenue,
            icons = R.drawable.baseline_currency_exchange_24,
            color = Blue500
        ),
        Stats(
            statsText = "Total Sales",
            total = totalSales.toLong(),
            icons = R.drawable.baseline_stacked_line_chart_24,
            color = PrimaryBlue
        ),
        Stats(
            statsText = "Out of Stock",
            total = outOfStock.toLong(),
            icons = R.drawable.baseline_inbox_24,
            color = SecondaryBlue
        )
    )


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {

        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.height(400.dp)) {
            items(statsData) {
                StatsCard(
                    icons = it.icons,
                    totalStats = it.total,
                    statsText = it.statsText,
                    cardColor = it.color
                )
            }
        }
        
        Text(text = "Sales Today", modifier = Modifier.padding(10.dp))

        Text(
            modifier = Modifier.padding(10.dp),
            text = Extensions.toRupiah(revenue),
            fontWeight = FontWeight(WidgetConstants.FONT_WEIGHT_BOLD),
            fontSize = WidgetConstants.XL_FONT_SIZE.sp
        )

        Cartesian(
            modelProducer = modelProducer
        )


    }

}