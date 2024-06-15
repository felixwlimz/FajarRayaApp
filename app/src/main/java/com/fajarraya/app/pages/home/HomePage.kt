package com.fajarraya.app.pages.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fajarraya.app.components.cards.StatsCard
import com.fajarraya.app.R
import com.fajarraya.app.models.Stats
import com.fajarraya.app.ui.theme.Blue500
import com.fajarraya.app.ui.theme.Blue600
import com.fajarraya.app.ui.theme.PrimaryBlue
import com.fajarraya.app.ui.theme.SecondaryBlue


@Composable
fun HomePage(modifier : Modifier = Modifier){

    val statsData = listOf(
        Stats(statsText = "Products", total = 250, icons = R.drawable.baseline_inbox_24, color = Blue600),
        Stats(statsText = "Revenue", total = 5500000, icons = R.drawable.baseline_currency_exchange_24, color = Blue500),
        Stats(statsText = "Total Sales", total = 50, icons = R.drawable.baseline_stacked_line_chart_24, color = PrimaryBlue),
        Stats(statsText = "Out of Stock", total = 10, icons = R.drawable.baseline_inbox_24, color = SecondaryBlue)
    )

        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {


            item {
                LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.height(500.dp)) {
                    items(statsData){
                        StatsCard(icons = it.icons, totalStats = it.total, statsText = it.statsText, cardColor = it.color)
                    }
                }


            }

    }

}