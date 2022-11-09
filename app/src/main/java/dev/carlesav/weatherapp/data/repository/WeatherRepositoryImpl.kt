package dev.carlesav.weatherapp.data.repository

import arrow.core.Either
import dev.carlesav.weatherapp.data.datasource.WeatherRemoteDataSource
import dev.carlesav.weatherapp.domain.FailureBo
import dev.carlesav.weatherapp.domain.repository.DomainLayerContract
import dev.carlesav.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
) : DomainLayerContract.Data.WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Either<FailureBo, WeatherInfo> =
        weatherRemoteDataSource.getWeatherData(lat, long)
}