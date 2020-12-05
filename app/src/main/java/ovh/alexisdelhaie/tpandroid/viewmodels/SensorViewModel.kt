package ovh.alexisdelhaie.tpandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ovh.alexisdelhaie.tpandroid.pojos.BatteryValue
import ovh.alexisdelhaie.tpandroid.repositories.SensorRepository

class SensorViewModel: ViewModel() {

    private val repo by lazy { SensorRepository() }

    var nextBatteryValue = MutableLiveData<BatteryValue>()

    init {
        nextBatteryValue = repo.nextBatteryValue
    }

    fun getInformation() {
        repo.getInformation()
    }

}