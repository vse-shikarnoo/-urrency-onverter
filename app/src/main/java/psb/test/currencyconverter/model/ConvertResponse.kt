package psb.test.currencyconverter.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConvertResponse(
    @SerialName("success") val success: Boolean,
    @SerialName("info") val info: Info,
    @SerialName("date") val date: String,
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("result") val result: Double,
)
