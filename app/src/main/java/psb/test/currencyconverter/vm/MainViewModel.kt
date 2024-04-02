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
    private var errorLiveData = MutableLiveData<Unit>()

    val valuteInfo: LiveData<ValuteSearchResponse>
        get() = valuteInfoLiveData

    val errorData: LiveData<Unit>
        get() = errorLiveData

    fun getValuteInfo() {
        viewModelScope.launch(Dispatchers.IO){
            try{
                while (isActive) {
                    valuteInfoLiveData.postValue(repository.getValuteInfo())
                    Log.e("Test", valuteInfoLiveData.toString())
                    delay(30000)
                }
            }catch(e:Throwable){
                errorLiveData.postValue(Unit)
            }

        }
    }
}