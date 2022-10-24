package com.example.mynotesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynotesapp.Dao.NotesDao
import com.example.mynotesapp.Model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false) //creating the database using Notes
abstract class NotesDatabase: RoomDatabase() { //extending to the ROOM database
    abstract fun myNotesDao(): NotesDao

    companion object{
        @Volatile //makes INSTANCE easily accessible
        var INSTANCE: NotesDatabase? = null

        //returns NotesDatabase
        fun getDatabaseInstance(context: Context): NotesDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){  //this means that ROOM database is not created
                //creating ROOM database with the name 'Notes'
                val roomDatabaseInstance = Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    "Notes").allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}