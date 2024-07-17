package psb.test.currencyconverter.repository

import psb.test.currencyconverter.model.Currency
import psb.test.currencyconverter.remote.ConvertService
import psb.test.currencyconverter.remote.CurrencyService

class MainRepository {

    private val currencyService = CurrencyService.create()
    private val convertService = ConvertService.create()

    suspend fun getCurrencies(): List<Currency> = currencyService.getCurrencies()

    suspend fun convert(from: String, to: String, amount: Double) =
        convertService.convert(from, to, amount)
}