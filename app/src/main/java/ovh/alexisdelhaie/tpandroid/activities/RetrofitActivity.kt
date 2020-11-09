package ovh.alexisdelhaie.tpandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_retrofit.*
import ovh.alexisdelhaie.tpandroid.R
import ovh.alexisdelhaie.tpandroid.activities.eventcallbacks.OnClickCallback
import ovh.alexisdelhaie.tpandroid.adapters.PeopleAdapter
import ovh.alexisdelhaie.tpandroid.pojos.People
import ovh.alexisdelhaie.tpandroid.pojos.SimpleObject
import ovh.alexisdelhaie.tpandroid.viewmodels.PeopleViewModel
import java.lang.Exception

class RetrofitActivity : AppCompatActivity(), OnClickCallback<People> {

    private lateinit var mAdapter: PeopleAdapter
    private lateinit var mViewModel: PeopleViewModel
    private var mObserverAndroidVersion = Observer<List<People>> {
        updateUi(ArrayList(it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        mViewModel = ViewModelProvider(this)[PeopleViewModel::class.java]
        mAdapter = PeopleAdapter(this)
        peopleRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        peopleRecyclerView.adapter = mAdapter
    }

    override fun onStart() {
        super.onStart()
        mViewModel.mChuckNorrisQuoteLiveData.observe(this, mObserverAndroidVersion)
    }


    override fun onStop() {
        mViewModel.mChuckNorrisQuoteLiveData.removeObserver(mObserverAndroidVersion)
        super.onStop()
    }


    private fun updateUi(newList: ArrayList<People>) {
        mAdapter.rebuild(newList)
    }


    fun onAddClick(v: View) {
        mViewModel.fetchNewPeople()
    }


    fun onClearClick(v: View) {
        mViewModel.deleteAll()
    }

    override fun onItemClick(obj: People) {
        val i: Intent = Intent(this, PeopleDetailActivity::class.java).also {
            it.putExtra("photoUrl", obj.picture.large)
            it.putExtra("name", String.format("%s %s", obj.name.first, obj.name.last))
            it.putExtra("gender", obj.gender)
            it.putExtra("street", String.format("%s %s", obj.location.street.number, obj.location.street.name))
            it.putExtra("city", obj.location.city)
            it.putExtra("state", obj.location.state)
            it.putExtra("country", obj.location.country)
            it.putExtra("email", obj.email)
        }
        startActivity(i)
    }

}