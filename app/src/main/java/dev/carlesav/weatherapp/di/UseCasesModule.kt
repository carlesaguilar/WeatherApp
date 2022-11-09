package dev.carlesav.weatherapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.carlesav.weatherapp.domain.location.LocationTracker
import dev.carlesav.weatherapp.domain.repository.DomainLayerContract
import dev.carlesav.weatherapp.domain.usecase.GetWeatherUseCase

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {
    @ViewModelScoped
    @Provides
    fun provideGetWeatherUseCase(
        repository: DomainLayerContract.Data.WeatherRepository,
        locationTracker: LocationTracker,
    ): GetWeatherUseCase =
        GetWeatherUseCase(repository, locationTracker)
}