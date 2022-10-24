package com.example.mynotesapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mynotesapp.Model.Notes

@Dao
interface NotesDao {
    //to fetch all notes from the database
    @Query("SELECT * FROM Notes")
    fun getAllNotes():LiveData<List<Notes>>

    //to fetch general notes (yellow) from the database
    @Query("SELECT * FROM Notes WHERE color='yellow'")
    fun getGeneralNotes():LiveData<List<Notes>>

    //to fetch study notes (pink) from the database
    @Query("SELECT * FROM Notes WHERE color='pink'")
    fun getStudyNotes():LiveData<List<Notes>>

    //to fetch work notes (green) from the database
    @Query("SELECT * FROM Notes WHERE color='green'")
    fun getWorkNotes():LiveData<List<Notes>>

    //to fetch personal notes (blue) from the database
    @Query("SELECT * FROM Notes WHERE color='blue'")
    fun getPersonalNotes():LiveData<List<Notes>>

    //to insert a note into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    //to delete a note from the database using its id
    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id: Int)

    //to update an existing note
    @Update()
    fun updateNotes(notes: Notes)
}