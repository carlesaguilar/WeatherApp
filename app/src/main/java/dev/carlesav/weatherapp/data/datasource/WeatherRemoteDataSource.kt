package dev.carlesav.weatherapp.data.datasource

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dev.carlesav.weatherapp.data.FailureDto
import dev.carlesav.weatherapp.data.mappers.toWeatherInfo
import dev.carlesav.weatherapp.data.remote.WeatherApi
import dev.carlesav.weatherapp.domain.FailureBo
import dev.carlesav.weatherapp.domain.toFailureBo
import dev.carlesav.weatherapp.domain.weather.WeatherInfo
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private val api: WeatherApi,
) : DataLayerContract.WeatherDataSource.Remote {
    override suspend fun getWeatherData(lat: Double, long: Double): Either<FailureBo, WeatherInfo> {
        return try {
            api.getWeatherData(lat = lat, long = long).toWeatherInfo().right()
        } catch (e: HttpException) {
            FailureDto.Exception(type = e.code().toString(), message = e.message()).toFailureBo()
                .left()
        } catch (e: Exception) {
            FailureDto.Unknown.toFailureBo().left()
        }
    }
}