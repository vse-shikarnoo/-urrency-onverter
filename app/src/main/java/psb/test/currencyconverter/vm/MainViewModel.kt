package psb.test.currencyconverter.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import psb.test.currencyconverter.model.ConvertResponse
import psb.test.currencyconverter.model.Currency
import psb.test.currencyconverter.repository.MainRepository

class MainViewModel() : ViewModel() {
    private val repository = MainRepository()


    private var currencyListLiveData = MutableLiveData<List<Currency>>()
    private var convertDataLiveData = MutableLiveData<ConvertResponse>()
    private var errorLiveData = MutableLiveData<Unit>()


    val currencyList: LiveData<List<Currency>>
        get() = currencyListLiveData
    val convertData: LiveData<ConvertResponse>
        get() = convertDataLiveData
    val error: LiveData<Unit>
        get() = errorLiveData


    fun getCurrencies() {
        viewModelScope.launch {
            try {
                currencyListLiveData.postValue(repository.getCurrencies())
            }catch (t:Throwable){
                Log.e("ERROR", "getCurrencies: ", t)
            }
        }
    }

    fun convert(from: String, to: String, amount: Double) {
        viewModelScope.launch {
            try {
                convertDataLiveData.postValue(repository.convert(from, to, amount))
            }catch (t:Throwable){
                Log.e("ERROR", "convert: ", t)
            }
        }
    }
}