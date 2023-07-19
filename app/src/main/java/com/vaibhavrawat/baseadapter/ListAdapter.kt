package com.vaibhavrawat.baseadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class ListAdapter(var arrayList: ArrayList<StudentData>, var listClickInterface: ListClickInterface) : BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return 1
    }
    override fun getItemId(position: Int): Long {
        return 1L
    }
    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.items_list_layout, parent, false)
        val name = view.findViewById<TextView>(R.id.tvName)
        val rollNo = view.findViewById<TextView>(R.id.tvRollNo)
        val btnUpdate = view.findViewById<Button>(R.id.btnUpdate)
        val btnDelete = view.findViewById<Button>(R.id.btnDelete)
        name.setText(arrayList[position].name)
        rollNo.setText(arrayList[position].rollNo.toString())
        btnUpdate.setOnClickListener {
            listClickInterface.onUpdateClick(position)
        }
        btnDelete.setOnClickListener {
            listClickInterface.onDelete(position)
        }
        return view
    }
}