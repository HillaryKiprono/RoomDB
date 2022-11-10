package com.kipronohillary.roomdb.models

import androidx.room.*


@Dao
interface StudentDao {
    //all the queries
    @Query("SELECT * FROM STUDENT_TABLE")
    fun getAll(): List<Student>

    @Query("SELECT * FROM student_table WHERE roll_no LIKE :roll LIMIT 1")
     fun findByRoll(roll: Int): Student

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(student: Student)

    @Delete
     fun delete(student: Student)

    @Query("DELETE FROM student_table")
     fun deleteAll()
     @Query("UPDATE student_table SET first_name=:firstName,last_name=:lastName WHERE  roll_no LIKE :roll")
     fun update(firstName:String,lastName:String,roll:Int)
}