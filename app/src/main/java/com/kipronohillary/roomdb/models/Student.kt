package com.kipronohillary.roomdb.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "first_name") val firstName:String,
    @ColumnInfo(name = "last_name") val lastName:String,
    @ColumnInfo(name = "reg_no") val RegNo:String
)
