package ovh.alexisdelhaie.tpandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_people_detail.*
import kotlinx.android.synthetic.main.item_people_layout.*
import ovh.alexisdelhaie.tpandroid.R

class PeopleDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_detail)
        val url = intent.getStringExtra("photoUrl")
        item_name_detail.text = intent.getStringExtra("name")
        item_gender.text = intent.getStringExtra("gender")
        item_street.text = intent.getStringExtra("street")
        item_city.text = intent.getStringExtra("city")
        item_state.text = intent.getStringExtra("state")
        item_country.text = intent.getStringExtra("country")
        item_email_detail.text = intent.getStringExtra("email")
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(item_photo_detail)
    }
}