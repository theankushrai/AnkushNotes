package com.example.ankushnotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)
    @Delete
    suspend fun delete(note:Note)
    @Query("select * from notes_table order by _id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}