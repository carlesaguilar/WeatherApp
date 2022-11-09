package dev.carlesav.weatherapp.presentation.weather_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.carlesav.weatherapp.R
import dev.carlesav.weatherapp.domain.FailureBo

@Composable
fun WeatherError(error: FailureBo) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        val errorMessage = when (error) {
            is FailureBo.Error -> {
                "${stringResource(id = R.string.error_detail)} ${error.code} ${error.message}"
            }
            else -> {
                stringResource(id = R.string.generic_error)
            }
        }
        Text(
            text = errorMessage,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}