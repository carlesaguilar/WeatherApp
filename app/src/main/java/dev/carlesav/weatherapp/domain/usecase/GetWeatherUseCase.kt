package dev.carlesav.weatherapp.domain.usecase

import arrow.core.Either
import arrow.core.left
import dev.carlesav.weatherapp.domain.FailureBo
import dev.carlesav.weatherapp.domain.location.LocationTracker
import dev.carlesav.weatherapp.domain.repository.DomainLayerContract
import dev.carlesav.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: DomainLayerContract.Data.WeatherRepository,
    private val locationTracker: LocationTracker,
) {
    suspend operator fun invoke(): Either<FailureBo, WeatherInfo> {
        locationTracker.getCurrentLocation()?.let { location ->
            return weatherRepository.getWeatherData(location.latitude, location.longitude)
        } ?: kotlin.run {
            return FailureBo.Unknown.left()
        }
    }
}