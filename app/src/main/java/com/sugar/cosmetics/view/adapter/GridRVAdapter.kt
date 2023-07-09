package com.sugar.cosmetics.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.sugar.cosmetics.R


class GridRVAdapter(
    private val key: ArrayList<String>,
    private val value: ArrayList<Double>,
    private val context: Context
) : BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var title: TextView
    private lateinit var rates: TextView
    override fun getCount(): Int {
        return key.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.gridview_item, null)
        }
        // on below line we are initializing our course image view
        // and course text view with their ids.
        title = convertView!!.findViewById(R.id.textView_title)
        rates = convertView!!.findViewById(R.id.textView_rates)
        // on below line we are setting image for our course image view.
        title.setText(key.get(position))
        // on below line we are setting text in our course text view.
        rates.setText(value.get(position).toString())
        // at last we are returning our convert view.
        return convertView
    }


}