package dev.carlesav.weatherapp.presentation.weather_screen

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dev.carlesav.weatherapp.R
import dev.carlesav.weatherapp.presentation.ui.theme.DarkBlue
import dev.carlesav.weatherapp.presentation.ui.theme.DeepBlue
import dev.carlesav.weatherapp.presentation.weather_screen.components.WeatherCard
import dev.carlesav.weatherapp.presentation.weather_screen.components.WeatherError
import dev.carlesav.weatherapp.presentation.weather_screen.components.WeatherForecast

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val cameraPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    )

    LaunchedEffect(key1 = cameraPermissionState.allPermissionsGranted, block = {
        if (cameraPermissionState.allPermissionsGranted) {
            viewModel.loadWeatherInfo()
        } else {
            cameraPermissionState.launchMultiplePermissionRequest()
        }
    })

    val state = viewModel.state
    Column(modifier = Modifier
        .fillMaxSize()
        .background(DarkBlue),
        horizontalAlignment = Alignment.CenterHorizontally) {
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            cameraPermissionState.allPermissionsGranted.not() -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val textToShow = if (cameraPermissionState.shouldShowRationale) {
                        stringResource(R.string.location_permissions_rationale)
                    } else {
                        stringResource(R.string.location_permissions_message)
                    }
                    Text(
                        text = textToShow,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = { cameraPermissionState.launchMultiplePermissionRequest() }) {
                        Text(text = stringResource(R.string.request_permissions)) // todo
                    }
                }
            }
            state.error != null -> {
                WeatherError(state.error)
            }
            state.weatherInfo != null -> {
                WeatherCard(state = state, backgroundColor = DeepBlue)
                Spacer(modifier = Modifier.height(16.dp))
                WeatherForecast(state = viewModel.state)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { viewModel.onEvent(WeatherEvents.ReloadWeather) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = DeepBlue,
                        contentColor = Color.White)
                ) {
                    Text(stringResource(id = R.string.reload_weather))
                }
            }
        }
    }
}