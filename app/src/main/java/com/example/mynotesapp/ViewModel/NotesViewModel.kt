package com.example.mynotesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mynotesapp.Database.NotesDatabase
import com.example.mynotesapp.Model.Notes
import com.example.mynotesapp.Repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val repository: NotesRepository

    //this block of code will run first
    init{
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao() //assigning the dao
        repository = NotesRepository(dao) //assigning the repository
    }
    //connects the functions below to NotesRepository
    fun getGeneralNotes():LiveData<List<Notes>> = repository.getGeneralNotes()
    fun getStudyNotes():LiveData<List<Notes>> = repository.getStudyNotes()
    fun getWorkNotes():LiveData<List<Notes>> = repository.getWorkNotes()
    fun getPersonalNotes():LiveData<List<Notes>> = repository.getPersonalNotes()
    fun insertNotes(notes: Notes) = repository.insertNotes(notes)
    fun getAllNotes():LiveData<List<Notes>> = repository.getAllNotes()
    fun deleteNotes(id: Int) = repository.deleteNotes(id)
    fun updateNotes(notes: Notes) = repository.updateNotes(notes)
}