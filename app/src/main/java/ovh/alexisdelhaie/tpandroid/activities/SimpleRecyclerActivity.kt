package ovh.alexisdelhaie.tpandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ovh.alexisdelhaie.tpandroid.viewmodels.CustomViewModel
import ovh.alexisdelhaie.tpandroid.R
import ovh.alexisdelhaie.tpandroid.adapters.CustomAdapter
import ovh.alexisdelhaie.tpandroid.pojos.SimpleObject
import kotlinx.android.synthetic.main.simple_recycler_activity.*
import kotlin.random.Random

class SimpleRecyclerActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapter;
    private lateinit var viewModel : CustomViewModel
    private var observerMyData = Observer<List<SimpleObject>> {
        updateUi(it)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_recycler_activity)
        viewModel = ViewModelProvider(this)[CustomViewModel::class.java]
        adapter = CustomAdapter(this)
        myRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        myRecyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.mutable.observe(this, observerMyData)
    }

    override fun onStop() {
        viewModel.mutable.removeObserver(observerMyData)
        super.onStop()
    }

    private fun updateUi(data : List<SimpleObject>) {
        adapter.rebuild(data)
    }

    fun onAddButtonClick(v: View) {
        val size = viewModel.getSize()
        if (size != null) {
            viewModel.insertData("Nouvelle data", if (size % 2 == 0) R.drawable.image else R.drawable.oppressive_laughter)
        }
    }

}