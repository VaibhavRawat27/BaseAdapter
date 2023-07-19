package com.vaibhavrawat.baseadapter

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.vaibhavrawat.baseadapter.databinding.ActivityMainBinding
import com.vaibhavrawat.baseadapter.databinding.CustomLayoutAddBinding

class MainActivity : AppCompatActivity(), ListClickInterface {
    lateinit var listAdapter : ListAdapter
    lateinit var binding : ActivityMainBinding
    var arrayList = arrayListOf<StudentData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listAdapter = ListAdapter(arrayList, this)
        binding.list.adapter = listAdapter
//        arrayList.add(StudentData("ABC", 1))
//        arrayList.add(StudentData("BCA", 2))
//        arrayList.add(StudentData("CAB", 3))
        binding.fab.setOnClickListener {
            val dialog = Dialog(this)
            val dialogBinding = CustomLayoutAddBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.setCancelable(false)
            dialogBinding.btnAdd.setOnClickListener {
                val name = dialogBinding.etName.text?.toString()
                val roll = dialogBinding.etRoll.text?.toString()?.toInt()
                arrayList.add(StudentData(name, roll))
                listAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialogBinding.btnCancel.setOnClickListener {
                dialog.hide()
            }
            dialog.show()
        }
    }
    override fun onUpdateClick(position: Int) {
        println("Add clicked! $position")
        val dialog = Dialog(this)
        val dialogBinding = CustomLayoutAddBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(false)
        dialogBinding.btnAdd.setOnClickListener {
            val name = dialogBinding.etName.text?.toString()
            val roll = dialogBinding.etRoll.text?.toString()?.toInt()
            arrayList.removeAt(position)
            listAdapter.notifyDataSetChanged()
            arrayList.add(position, StudentData(name,roll))
            listAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialogBinding.btnCancel.setOnClickListener {
            dialog.hide()
        }
        dialog.show()
        Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show()
    }

    override fun onDelete(position: Int) {
        val deletedItem = arrayList[position]
        arrayList.removeAt(position)
        listAdapter.notifyDataSetChanged()
        Snackbar.make(binding.root, "Deleted successfully", Snackbar.LENGTH_LONG)
            .setAction("UNDO") {
                arrayList.add(position, deletedItem)
                listAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Undo done successfully!", Toast.LENGTH_SHORT).show()
            }.show()
        //arrayList.removeAt(position)
        listAdapter.notifyDataSetChanged()
        Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show()
    }
}