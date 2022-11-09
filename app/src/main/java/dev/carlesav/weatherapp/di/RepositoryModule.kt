package dev.carlesav.weatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.carlesav.weatherapp.data.repository.WeatherRepositoryImpl
import dev.carlesav.weatherapp.domain.repository.DomainLayerContract
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl,
    ): DomainLayerContract.Data.WeatherRepository
}