package com.kipronohillary.roomdb.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import java.text.Normalizer.Form

@Database(entities = [Student::class], version = 1)
 abstract class AppDatabase :RoomDatabase(){
  abstract fun studentDao():StudentDao

  companion object{
   @Volatile
   private var INSTANCE:AppDatabase?=null
   fun getDatabase(context: Context):AppDatabase{
    val tempInstance= INSTANCE
    if(tempInstance!=null){
     return  tempInstance
    }
    synchronized(this){
     val instance= Room.databaseBuilder(
      context.applicationContext,
      AppDatabase::class.java,
      "app_database"
     ).build()
     INSTANCE=instance
     return instance
    }
   }
  }
}