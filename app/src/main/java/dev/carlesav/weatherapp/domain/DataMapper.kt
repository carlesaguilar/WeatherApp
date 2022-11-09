package dev.carlesav.weatherapp.domain

import dev.carlesav.weatherapp.data.FailureDto

fun FailureDto.toFailureBo(): FailureBo {
    return when (this) {
        FailureDto.NoData -> FailureBo.NoData
        FailureDto.Unauthorized -> FailureBo.Unauthorized
        FailureDto.Forbidden -> FailureBo.Forbidden
        FailureDto.NoNetwork -> FailureBo.NoNetwork
        FailureDto.Unknown -> FailureBo.Unknown
        is FailureDto.Exception -> FailureBo.Error(type.orEmpty(), message.orEmpty())
        is FailureDto.Error -> FailureBo.Error(code.orEmpty(), message.orEmpty())
    }
}