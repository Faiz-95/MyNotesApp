package com.example.mynotesapp.UI.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynotesapp.Model.Notes
import com.example.mynotesapp.R
import com.example.mynotesapp.UI.Adapter.NotesAdapter
import com.example.mynotesapp.ViewModel.NotesViewModel
import com.example.mynotesapp.databinding.FragmentHomeBinding

@Suppress("DEPRECATION")
class FragmentHome : Fragment() {

    lateinit var binding:FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.notesRecyclerView.layoutManager = staggeredGridLayoutManager

        viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->
            oldNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), notesList)
            binding.notesRecyclerView.adapter = adapter
        }

        binding.generalButton.setOnClickListener {
            viewModel.getGeneralNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.notesRecyclerView.adapter = adapter
            }
        }

        binding.studyButton.setOnClickListener {
            viewModel.getStudyNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.notesRecyclerView.adapter = adapter
            }
        }

        binding.workButton.setOnClickListener {
            viewModel.getWorkNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.notesRecyclerView.adapter = adapter
            }
        }

        binding.personalButton.setOnClickListener {
            viewModel.getPersonalNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.notesRecyclerView.adapter = adapter
            }
        }

        binding.filterIcon.setOnClickListener{
            viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.notesRecyclerView.adapter = adapter
            }
        }

        binding.addNotesButton.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_fragmentHome_to_fragmentCreateNote)
        }
        return binding.root
    }

     override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_note, menu)
        val item = menu.findItem(R.id.menuSearchIcon)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter notes here...."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                notesFiltering(p0)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }



    private fun notesFiltering(p0: String?) {
        val filteredNotesList = arrayListOf<Notes>()
        for (i in oldNotes){
            if (i.title.contains(p0!!) || (i.subtitle.contains(p0))){
                filteredNotesList.add(i)
            }
        }
        adapter.filterNotes(filteredNotesList)
    }
}