package psb.test.currencyconverter.remote

import okhttp3.OkHttpClient
import psb.test.currencyconverter.model.ValuteSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CBService {

    //https://www.cbr-xml-daily.ru/daily_json.js
    @GET("daily_json.js")
    suspend fun getValuteInfo():ValuteSearchResponse

    companion object{
        fun create(): CBService{
            val okHttpClient = OkHttpClient.Builder()
                .build()


            return Retrofit.Builder()
                .baseUrl("https://www.cbr-xml-daily.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(CBService::class.java)
        }
    }
}