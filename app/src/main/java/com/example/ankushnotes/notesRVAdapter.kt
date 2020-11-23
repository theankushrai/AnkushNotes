package com.example.ankushnotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ankushnotes.databinding.ActivityMainBinding

class NotesRVAdapter(private val context:Context,  val listener:INotesRVAdaper):ListAdapter<Note,NotesRVAdapter.NotesViewHolder>(OnUpdateListCallback()) {


    inner class NotesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView:TextView=itemView.findViewById(R.id.DisplayNotetextView)
        val deleteButton :ImageView= itemView.findViewById(R.id.deleteNoteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClick(getItem(viewHolder.adapterPosition))
        }
        return  viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNode = getItem(position)
        holder.textView.text=currentNode.text
    }

}
interface INotesRVAdaper{
    fun onItemClick(note:Note)
}
class OnUpdateListCallback :DiffUtil.ItemCallback<Note>(){
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem._id==newItem._id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem==newItem
    }

}