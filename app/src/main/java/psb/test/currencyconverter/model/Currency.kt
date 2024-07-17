package psb.test.currencyconverter.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    @SerializedName("code") val code:String,
    @SerializedName("name") val name:String,
    @SerializedName("decimal_digits") val decimalDigits:String,
    @SerializedName("name_plural") val namePlural:String,
    @SerializedName("rounding") val rounding:String,
    @SerializedName("symbol") val symbol:String,
    @SerializedName("symbol_native") val symbolNative:String,
)
