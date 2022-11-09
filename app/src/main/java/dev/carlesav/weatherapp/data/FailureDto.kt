package dev.carlesav.weatherapp.data

sealed class FailureDto {
    class Error(val code: String?, val message: String?) : FailureDto()
    object Unauthorized : FailureDto()
    object Forbidden : FailureDto()
    object NoNetwork : FailureDto()
    object Unknown : FailureDto()
    object NoData : FailureDto()
    class Exception(val type: String?, val message: String?) : FailureDto()
}