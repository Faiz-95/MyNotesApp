package com.example.mynotesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mynotesapp.Database.NotesDatabase
import com.example.mynotesapp.Model.Notes
import com.example.mynotesapp.Repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {
    val repository: NotesRepository

    init{
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }
    fun getGeneralNotes():LiveData<List<Notes>> = repository.getGeneralNotes()
    fun getStudyNotes():LiveData<List<Notes>> = repository.getStudyNotes()
    fun getWorkNotes():LiveData<List<Notes>> = repository.getWorkNotes()
    fun getPersonalNotes():LiveData<List<Notes>> = repository.getPersonalNotes()
    fun insertNotes(notes: Notes) = repository.insertNotes(notes)
    fun getAllNotes():LiveData<List<Notes>> = repository.getAllNotes()
    fun deleteNotes(id: Int) = repository.deleteNotes(id)
    fun updateNotes(notes: Notes) = repository.updateNotes(notes)
}