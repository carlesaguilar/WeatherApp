package dev.carlesav.weatherapp.presentation.weather_screen

import dev.carlesav.weatherapp.domain.FailureBo
import dev.carlesav.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val isLoading: Boolean = false,
    val error: FailureBo? = null,
    val weatherInfo: WeatherInfo? = null,
)