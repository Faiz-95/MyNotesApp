package com.example.mynotesapp.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.Model.Notes
import com.example.mynotesapp.R
import com.example.mynotesapp.UI.Fragments.FragmentHomeDirections
import com.example.mynotesapp.databinding.NotesCardBinding

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    fun filterNotes(filteredNotesList: ArrayList<Notes>) {
        notesList = filteredNotesList
        notifyDataSetChanged()
    }


    class NotesViewHolder(val binding: NotesCardBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(NotesCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.noteCardTitleView.text = data.title
        holder.binding.noteCardSubtitleView.text = data.subtitle
        holder.binding.noteCardDateView.text = data.date

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
        holder.binding.root.setOnClickListener{
            val action = FragmentHomeDirections.actionFragmentHomeToFragmentEditNote(data)
            Navigation.findNavController(it).navigate(action)

        }
    }



    override fun getItemCount() = notesList.size
}