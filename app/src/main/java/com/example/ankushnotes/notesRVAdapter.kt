package com.example.ankushnotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context:Context,  val listener:INotesRVAdaper):RecyclerView.Adapter<NotesRVAdapter.NotesViewHolder>() {

    private val allNotes=ArrayList<Note>()

    inner class NotesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView:TextView=itemView.findViewById(R.id.DisplayNotetextView)
        val deleteButton :ImageView= itemView.findViewById(R.id.deleteNoteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClick(allNotes[viewHolder.adapterPosition])
        }
        return  viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNode = allNotes[position]
        holder.textView.text=currentNode.text
    }

    override fun getItemCount(): Int {
        return  allNotes.size
    }
    fun onUpdateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}
interface INotesRVAdaper{
    fun onItemClick(note:Note)
}