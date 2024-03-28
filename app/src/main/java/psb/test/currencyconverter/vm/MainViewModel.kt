package psb.test.currencyconverter.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import psb.test.currencyconverter.model.ValuteSearchResponse
import psb.test.currencyconverter.repository.MainRepository

class MainViewModel() : ViewModel() {
    private val repository = MainRepository()

    private var valuteInfoLiveData = MutableLiveData<ValuteSearchResponse>()

    val valuteInfo: LiveData<ValuteSearchResponse>
        get() = valuteInfoLiveData


    fun getValuteInfo() {
        viewModelScope.launch{

            while (isActive) {
                valuteInfoLiveData.postValue(repository.getValuteInfo())
                Log.e("Test", valuteInfoLiveData.toString())
                delay(30000)
            }
        }
    }
}