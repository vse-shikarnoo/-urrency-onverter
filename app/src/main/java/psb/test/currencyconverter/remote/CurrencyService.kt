package psb.test.currencyconverter.remote

import okhttp3.OkHttpClient
import psb.test.currencyconverter.model.Currency
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("b5829c35-a683-4096-8cc2-11b561c8e539")
    suspend fun getCurrencies(): List<Currency>

    companion object{
        fun create(): CurrencyService{
            val okHttpClient = OkHttpClient.Builder()
                .build()


            return Retrofit.Builder()
                .baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(CurrencyService::class.java)
        }
    }
}