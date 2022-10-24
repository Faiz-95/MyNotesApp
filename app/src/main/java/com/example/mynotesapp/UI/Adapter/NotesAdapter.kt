package com.example.mynotesapp.UI.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.Model.Notes
import com.example.mynotesapp.R
import com.example.mynotesapp.UI.Fragments.FragmentHomeDirections
import com.example.mynotesapp.databinding.NotesCardBinding

class NotesAdapter(var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    //using the existing view binding
    class NotesViewHolder(val binding: NotesCardBinding ) : RecyclerView.ViewHolder(binding.root)

    //function to set a new list with filtered notes when search option is used
    fun filterNotes(filteredNotesList: ArrayList<Notes>) {
        notesList = filteredNotesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(NotesCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = notesList[position] //to get the data of a particular note
        holder.binding.noteCardTitleView.text = data.title //setting the title of note using binding
        holder.binding.noteCardSubtitleView.text = data.subtitle //setting the subtitle of note using binding
        holder.binding.noteCardDateView.text = data.date //setting the date of note using binding

        //setting the note color
        when (data.color){
            "yellow" -> {
                holder.binding.noteCard.setBackgroundResource(R.color.light_yellow)
            }
            "pink" -> {
                holder.binding.noteCard.setBackgroundResource(R.color.light_pink)
            }
            "green" -> {
                holder.binding.noteCard.setBackgroundResource(R.color.light_green)
            }
            "blue" -> {
                holder.binding.noteCard.setBackgroundResource(R.color.light_blue)
            }
        }

        //when user clicks on a note
        holder.binding.root.setOnClickListener{
            val action = FragmentHomeDirections.actionFragmentHomeToFragmentEditNote(data)
            //navigate to the edit note page once the user clicks on the note
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = notesList.size //return size of the list
}