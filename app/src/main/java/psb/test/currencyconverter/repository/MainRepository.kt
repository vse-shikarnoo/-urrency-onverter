package psb.test.currencyconverter.repository

import psb.test.currencyconverter.model.ValuteSearchResponse
import psb.test.currencyconverter.remote.CBService

class MainRepository {
    suspend fun getValuteInfo():ValuteSearchResponse{
        return CBService.create().getValuteInfo()
    }
}