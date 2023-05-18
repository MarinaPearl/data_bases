package ru.demchuk.database.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ru.demchuk.database.R

class ListViewAdapter(val context: Context, val list: ArrayList<String>) : BaseAdapter() {

    private var layout: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = layout.inflate(R.layout.item_layout, parent, false)
        }
        val text = view?.findViewById(R.id.text) as TextView
        text.text = list[position]
        text.setTextColor(Color.BLACK)
        return view
    }

}