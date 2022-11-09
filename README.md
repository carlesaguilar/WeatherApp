# Weather App

Application developed in Kotlin using Clean Architecture principles based
on https://github.com/philipplackner/WeatherApp

The open-meteo.com API is used to show the weather details related to your location.

## Architecture details

For the development of this application, it has been divided into different directories:
*data*, *domain* and *presentation*.

*usecases* are used to connect the presentation layer with the domain layer.

Other improvements:

- Added repository and datasource patterns.
- Functional Programming using Λrrow library
- Accompanist library
- UI improvements

## Used libraries

- MVI & Viewmodel
- Coroutinas
- Dagger Hilt
- Retrofit 2
- Flow
- Jetpack Compose
- Material Design
- Coil
- Moshi
