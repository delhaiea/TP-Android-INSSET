package ovh.alexisdelhaie.tpandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item_detail.*
import ovh.alexisdelhaie.tpandroid.R

class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val id = intent.getIntExtra("drawableId", 0)
        detailImageView.setImageResource(id)
    }
}