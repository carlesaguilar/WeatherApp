package dev.carlesav.weatherapp.domain

sealed class FailureBo {
    object Unauthorized : FailureBo()
    object Forbidden : FailureBo()
    object NoNetwork : FailureBo()
    object Unknown : FailureBo()
    object InputParamsError : FailureBo()
    object NoData : FailureBo()
    open class Error(val code: String, val message: String) : FailureBo()
}