package psb.test.currencyconverter.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Query(
    @SerializedName("from") val from: String,
    @SerializedName("to") val to: String,
    @SerializedName("amount") val amount: String,
)
