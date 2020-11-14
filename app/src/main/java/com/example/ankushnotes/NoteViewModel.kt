package com.example.ankushnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allnotes:LiveData<List<Note>>
    private val repository:NoteRepository

    init {
        val dao=NotesDatabase.getDatabase(application).getNoteDao()
        repository=NoteRepository(dao)
        allnotes=repository.allNotes
    }

    fun deleteNote(note:Note)=viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun insertNote(note:Note)=viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}