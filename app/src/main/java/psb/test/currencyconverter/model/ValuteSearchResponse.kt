package psb.test.currencyconverter.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ValuteSearchResponse(
    @SerializedName("Date") val date: String,
    @SerializedName("Timestamp") val timestamp: String,
    @SerializedName("Valute") val valute: Map<String,ValuteInfo>,
)
