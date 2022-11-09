package dev.carlesav.weatherapp.presentation.weather_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlesav.weatherapp.domain.FailureBo
import dev.carlesav.weatherapp.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
) : ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            getWeatherUseCase().fold({ error ->
                state = state.copy(
                    isLoading = false,
                    error = error,
                )
            }, { weatherInfo ->
                state = state.copy(
                    isLoading = false,
                    weatherInfo = weatherInfo
                )
            })
        }
    }

    fun onEvent(event: WeatherEvents) {
        when (event) {
            is WeatherEvents.PermissionsNotGranted -> {
                state = state.copy(error = FailureBo.Error("PERMISSIONS_NOT_GRANTED", ""))
            }
            WeatherEvents.ReloadWeather -> loadWeatherInfo()
        }
    }
}