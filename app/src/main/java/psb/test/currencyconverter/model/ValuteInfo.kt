package psb.test.currencyconverter.model

import com.fasterxml.jackson.annotation.JsonRootName
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ValuteInfo(
    @SerializedName("CharCode") val charCode: String,
    @SerializedName("Name") val name: String,
    @SerializedName("Value") val value: Float,
    @SerializedName("Previous") val prevValue: Float,
)
