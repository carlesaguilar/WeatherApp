package dev.carlesav.weatherapp.domain.repository

import arrow.core.Either
import dev.carlesav.weatherapp.domain.FailureBo
import dev.carlesav.weatherapp.domain.weather.WeatherInfo

interface DomainLayerContract {
    interface Data {
        interface WeatherRepository {
            suspend fun getWeatherData(lat: Double, long: Double): Either<FailureBo, WeatherInfo>
        }
    }
}