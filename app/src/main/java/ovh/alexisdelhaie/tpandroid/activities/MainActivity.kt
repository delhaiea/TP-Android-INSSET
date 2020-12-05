package ovh.alexisdelhaie.tpandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ovh.alexisdelhaie.tpandroid.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onRecyclerButtonClick(v: View) {
        val i = Intent(this, SimpleRecyclerActivity::class.java)
        startActivity(i)
    }

    fun onRetrofitButtonClick(v: View) {
        val i = Intent(this, RetrofitActivity::class.java)
        startActivity(i)
    }

    fun onOpenLoginButtonClick(v: View) {
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }

    fun onOpenBatteryButtonClick(v: View) {
        val i = Intent(this, BatteryActivity::class.java)
        startActivity(i)
    }

}