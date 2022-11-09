package dev.carlesav.weatherapp.presentation.weather_screen

sealed class WeatherEvents {
    object ReloadWeather : WeatherEvents()
    object PermissionsNotGranted : WeatherEvents()
}
