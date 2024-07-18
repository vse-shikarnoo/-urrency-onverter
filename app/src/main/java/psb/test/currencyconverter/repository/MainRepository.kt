package psb.test.currencyconverter.repository

import android.util.Log
import psb.test.currencyconverter.model.ConvertResponse
import psb.test.currencyconverter.model.Currency
import psb.test.currencyconverter.remote.ConvertService
import psb.test.currencyconverter.remote.CurrencyService

class MainRepository {

    private val currencyService = CurrencyService.create()
    private val convertService = ConvertService.create()

    suspend fun getCurrencies(): List<Currency> = currencyService.getCurrencies()

    suspend fun convert(
        from: String,
        to: String,
        amount: Double
    ): ConvertResponse {
        return convertService.convert(from, to, amount)
    }

}