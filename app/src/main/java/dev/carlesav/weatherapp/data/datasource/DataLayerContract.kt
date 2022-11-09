package dev.carlesav.weatherapp.data.datasource

import arrow.core.Either
import dev.carlesav.weatherapp.domain.FailureBo
import dev.carlesav.weatherapp.domain.weather.WeatherInfo

interface DataLayerContract {
    interface WeatherDataSource {
        interface Remote {
            suspend fun getWeatherData(lat: Double, long: Double): Either<FailureBo, WeatherInfo>
        }
    }
}