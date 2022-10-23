package com.example.mynotesapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mynotesapp.Model.Notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM Notes")
    fun getAllNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE color='yellow'")
    fun getGeneralNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE color='pink'")
    fun getStudyNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE color='green'")
    fun getWorkNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE color='blue'")
    fun getPersonalNotes():LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id: Int)

    @Update()
    fun updateNotes(notes: Notes)
}