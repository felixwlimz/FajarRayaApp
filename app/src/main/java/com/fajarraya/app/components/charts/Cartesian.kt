package com.fajarraya.app.components.charts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberEndAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer

@Composable
fun Cartesian(modifier : Modifier = Modifier, modelProducer: CartesianChartModelProducer){
 
    Box(modifier = modifier
        .height(200.dp)
        .fillMaxWidth()
        .padding(10.dp) ){
        CartesianChartHost(
            modifier = Modifier.fillMaxSize(),
            chart = rememberCartesianChart(
                rememberLineCartesianLayer(),
                startAxis = rememberStartAxis(),
                endAxis = rememberEndAxis()
            ),
            modelProducer = modelProducer
        )
    }
    
}