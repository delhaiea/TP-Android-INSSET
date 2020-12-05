package ovh.alexisdelhaie.tpandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_battery.*
import ovh.alexisdelhaie.tpandroid.R
import ovh.alexisdelhaie.tpandroid.pojos.BatteryValue
import ovh.alexisdelhaie.tpandroid.viewmodels.SensorViewModel

class BatteryActivity : AppCompatActivity() {

    private lateinit var viewModel: SensorViewModel

    private var observer = Observer<BatteryValue> {
        getLastValue(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battery)

        viewModel = ViewModelProvider(this)[SensorViewModel::class.java]
        viewModel.getInformation()
    }

    override fun onStart() {
        super.onStart()
        viewModel.nextBatteryValue.observe(this, observer)
    }


    override fun onStop() {
        viewModel.nextBatteryValue.removeObserver(observer)
        super.onStop()
    }

    fun onRefreshButtonClick(v: View) {
        viewModel.getInformation()
    }

    private fun getLastValue(battery: BatteryValue) {
        batteryVoltage.text = battery.voltage.toString()
        batteryLevel.text = battery.percentage.toString()
    }
}