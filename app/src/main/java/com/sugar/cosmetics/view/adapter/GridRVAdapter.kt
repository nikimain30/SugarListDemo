package com.sugar.cosmetics.view.adapter

import android.annotation.SuppressLint
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

    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.gridview_item, null)
        }
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