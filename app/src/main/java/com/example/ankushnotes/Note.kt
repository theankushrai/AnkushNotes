package com.example.ankushnotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(@ColumnInfo(name = "text") val text:String,
                @PrimaryKey(autoGenerate = true) var _id: Int =0)