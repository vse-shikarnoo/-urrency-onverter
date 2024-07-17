package psb.test.currencyconverter.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ConvertService {

    @GET("convert")
    suspend fun convert(
        @Query("from") from:String,
        @Query("to") to:String,
        @Query("amount") amount:Double,
    )

    companion object{
        fun create(): ConvertService{
            val okHttpClient = OkHttpClient.Builder()
                .build()


            return Retrofit.Builder()
                .baseUrl("https://api.fxratesapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ConvertService::class.java)
        }
    }
}