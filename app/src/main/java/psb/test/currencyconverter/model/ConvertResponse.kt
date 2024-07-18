package psb.test.currencyconverter.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ConvertResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("query") val query: Query,
    @SerializedName("info") val info: Info,
    @SerializedName("date") val date: String,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("result") val result: Double,
)
