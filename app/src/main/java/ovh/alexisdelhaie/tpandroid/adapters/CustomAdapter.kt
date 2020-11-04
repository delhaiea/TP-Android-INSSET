package ovh.alexisdelhaie.tpandroid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ovh.alexisdelhaie.tpandroid.R
import ovh.alexisdelhaie.tpandroid.pojos.SimpleObject

class CustomAdapter(private val context: Context):
    RecyclerView.Adapter<CustomAdapter.MyCustomViewHolder>() {

    private val data: ArrayList<SimpleObject> = ArrayList()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class MyCustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.item_recycler_image)
        val itemValue: TextView = itemView.findViewById(R.id.item_recycler_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        return MyCustomViewHolder(inflater.inflate(R.layout.item_custom_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        val o = data[position]
        holder.itemImage.setImageResource(o.drawable)
        holder.itemValue.text = o.text
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun rebuild(newData: List<SimpleObject>) {
        data.clear()
        data.addAll(newData)
        this.notifyDataSetChanged()
    }

}