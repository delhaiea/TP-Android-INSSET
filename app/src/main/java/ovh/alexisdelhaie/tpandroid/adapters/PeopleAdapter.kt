package ovh.alexisdelhaie.tpandroid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ovh.alexisdelhaie.tpandroid.R
import ovh.alexisdelhaie.tpandroid.activities.eventcallbacks.OnClickCallback
import ovh.alexisdelhaie.tpandroid.pojos.People
import ovh.alexisdelhaie.tpandroid.pojos.SimpleObject

class PeopleAdapter(private val context: Context): RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    inner class PeopleViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val names: TextView = v.findViewById(R.id.item_name)
        val email: TextView = v.findViewById(R.id.item_email)
        val photo: ImageView = v.findViewById(R.id.item_photo)

        private fun itemClicked() {
            mCallback.onItemClick(
                this@PeopleAdapter.peoples[adapterPosition])
        }

        init {
            this.itemView.setOnClickListener { itemClicked() }
        }
    }

    private val peoples = ArrayList<People>()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    private val mCallback = context as OnClickCallback<People>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder(mInflater.inflate(R.layout.item_people_layout, parent, false))
    }

    override fun getItemCount(): Int  = peoples.size

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val people = peoples[position]
        holder.names.text = String.format("%s %s", people.name.first, people.name.last)
        holder.email.text = people.email
        Glide.with(context)
            .load(people.picture.medium)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.photo)
    }

    fun rebuild(newData: List<People>) {
        peoples.clear()
        peoples.addAll(newData)
        this.notifyDataSetChanged()
    }

}