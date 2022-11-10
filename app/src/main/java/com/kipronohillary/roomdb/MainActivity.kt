package com.kipronohillary.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kipronohillary.roomdb.databinding.ActivityMainBinding
import com.kipronohillary.roomdb.models.AppDatabase
import com.kipronohillary.roomdb.models.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appDb:AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb=AppDatabase.getDatabase(this)
        binding.btnWriteData.setOnClickListener {
            writeData()
        }
        binding.btnReadData.setOnClickListener {
            readData()
        }
        binding.btnDeleteAll.setOnClickListener {
            GlobalScope.launch {
                appDb.studentDao().deleteAll()
            }
        }
        binding.btnupdate.setOnClickListener {
            updateData()
        }

    }

    private fun updateData() {
        val firstname=binding.etFirstName.text.toString()
        val lastname=binding.etLastName.text.toString()
        val rollNo=binding.etRollNo.text.toString()
        if (firstname.isNotEmpty() && lastname.isNotEmpty() && rollNo.isNotEmpty()){

            GlobalScope.launch (Dispatchers.IO){
                appDb.studentDao().update(firstname,lastname,rollNo.toInt())
            }
            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()
            binding.etRollNo.text.clear()
            Toast.makeText(this@MainActivity, "Successfully updated", Toast.LENGTH_SHORT).show()


        }
        else
        {
            Toast.makeText(this@MainActivity, "Please enter the data", Toast.LENGTH_SHORT).show()
        }
    }


    private fun writeData() {
        val firstname=binding.etFirstName.text.toString()
        val lastname=binding.etLastName.text.toString()
        val rollNo=binding.etRollNo.text.toString()
        if (firstname.isNotEmpty() && lastname.isNotEmpty() && rollNo.isNotEmpty()){
            val student=Student(
                    null,firstname,lastname,rollNo.toInt()
            )
            GlobalScope.launch (Dispatchers.IO){
                appDb.studentDao().insert(student)
            }
            binding.etFirstName.text.clear()
            binding.etLastName.text.clear()
            binding.etRollNo.text.clear()
            Toast.makeText(this@MainActivity, "Successfully Inserted", Toast.LENGTH_SHORT).show()


        }
        else
        {
            Toast.makeText(this@MainActivity, "Please enter the data", Toast.LENGTH_SHORT).show()
        }
    }
    private  suspend fun displayData(student: Student){
        withContext(Dispatchers.Main){
            binding.tvFirstName.text=student.firstName
            binding.tvLastName.text=student.lastName
            binding.tvRollNo.text=student.rollNo.toString()
        }
    }
    private fun readData() {
        val rollNo=binding.etRollNoRead.text.toString()
        if (rollNo.isNotEmpty()){
            lateinit var student: Student

            GlobalScope.launch {
                student=appDb.studentDao().findByRoll(rollNo.toInt())
                displayData(student)
            }
        }
    }

}