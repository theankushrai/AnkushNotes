 package com.example.ankushnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ankushnotes.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity(), INotesRVAdaper {

    lateinit var viewModel: NoteViewModel

     override fun onCreate(savedInstanceState: Bundle?) {
//        val binding=ActivityMainBinding.inflate(layoutInflater)
//         val view=

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NotesRVAdapter(this,this)
        recyclerView.adapter=adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allnotes.observe(this, Observer{ list->
            list?.let {
                adapter.submitList(it) }
        })
        recyclerView.layoutManager=LinearLayoutManager(this)

    }

     override fun onItemClick(note: Note) {
        viewModel.deleteNote(note)
         Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show()
     }

     fun submitData(view: View) {
         val input:EditText=findViewById(R.id.inputEditText)
         val noteText =input.text.toString()
         if(noteText.isEmpty()){
             Toast.makeText(this , "Empty Note", Toast.LENGTH_SHORT).show()
         }
         else{
             viewModel.insertNote(Note(noteText))
         }
     }
 }