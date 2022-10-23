package com.example.mynotesapp.Repository

import androidx.lifecycle.LiveData
import com.example.mynotesapp.Dao.NotesDao
import com.example.mynotesapp.Model.Notes

class NotesRepository(val dao: NotesDao) {
    fun getAllNotes():LiveData<List<Notes>> = dao.getAllNotes()
    fun getGeneralNotes():LiveData<List<Notes>> = dao.getGeneralNotes()
    fun getStudyNotes():LiveData<List<Notes>> = dao.getStudyNotes()
    fun getWorkNotes():LiveData<List<Notes>> = dao.getWorkNotes()
    fun getPersonalNotes():LiveData<List<Notes>> = dao.getPersonalNotes()
    fun insertNotes(notes: Notes) = dao.insertNotes(notes)
    fun deleteNotes(id: Int) = dao.deleteNotes(id)
    fun updateNotes(notes: Notes) = dao.updateNotes(notes)
}