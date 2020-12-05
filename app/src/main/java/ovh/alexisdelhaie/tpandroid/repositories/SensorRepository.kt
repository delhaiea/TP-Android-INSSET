package ovh.alexisdelhaie.tpandroid.repositories

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ovh.alexisdelhaie.tpandroid.MyApplication
import ovh.alexisdelhaie.tpandroid.pojos.BatteryValue


class SensorRepository {

    private val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let {
        MyApplication.instance.applicationContext.registerReceiver(null, it)
    }

    private val postListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val post = dataSnapshot.getValue<BatteryValue>()
            nextBatteryValue.postValue(post)
        }

        override fun onCancelled(error: DatabaseError) { }
    }

    private val database = Firebase.database.reference
    var nextBatteryValue = MutableLiveData<BatteryValue>()

    init {
        database.addValueEventListener(postListener)
    }

    fun getInformation() {
        val voltage: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0) ?: 0
        val percentage: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1

        val bat = BatteryValue(voltage, percentage)
        database.setValue(bat)
    }

}