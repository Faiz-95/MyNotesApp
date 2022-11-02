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
    var oldNotes = arrayListOf<Notes>()  //contains details of all the notes of the user
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true) //true because we need to display the search icon
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        //makes the Grid Layout appear cleaner, using 2 columns
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.notesRecyclerView.layoutManager = staggeredGridLayoutManager

        //display all notes on the homepage by default
        viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->
            oldNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(notesList)
            binding.notesRecyclerView.adapter = adapter

        }

        //display only 'General' category notes (yellow) on the homepage
        binding.generalButton.setOnClickListener {
            viewModel.getGeneralNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(notesList)
                binding.notesRecyclerView.adapter = adapter
                //to change the transparency of the other categories when 'General' is clicked
                binding.allButton.alpha = 0.4F
                binding.generalButton.alpha = 1F
                binding.studyButton.alpha = 0.4F
                binding.workButton.alpha = 0.4F
                binding.personalButton.alpha = 0.4F
            }
        }

        //display only 'Study' category notes (pink) on the homepage
        binding.studyButton.setOnClickListener {
            viewModel.getStudyNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(notesList)
                binding.notesRecyclerView.adapter = adapter
                //to change the transparency of the other categories when 'Study' is clicked
                binding.allButton.alpha = 0.4F
                binding.generalButton.alpha = 0.4F
                binding.studyButton.alpha = 1F
                binding.workButton.alpha = 0.4F
                binding.personalButton.alpha = 0.4F
            }
        }

        //display only 'Work' category notes (green) on the homepage
        binding.workButton.setOnClickListener {
            viewModel.getWorkNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(notesList)
                binding.notesRecyclerView.adapter = adapter
                //to change the transparency of the other categories when 'Work' is clicked
                binding.allButton.alpha = 0.4F
                binding.generalButton.alpha = 0.4F
                binding.studyButton.alpha = 0.4F
                binding.workButton.alpha = 1F
                binding.personalButton.alpha = 0.4F
            }
        }

        //display only 'Personal' category notes (blue) on the homepage
        binding.personalButton.setOnClickListener {
            viewModel.getPersonalNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(notesList)
                binding.notesRecyclerView.adapter = adapter
                //to change the transparency of the other categories when 'Personal' is clicked
                binding.allButton.alpha = 0.4F
                binding.generalButton.alpha = 0.4F
                binding.studyButton.alpha = 0.4F
                binding.workButton.alpha = 0.4F
                binding.personalButton.alpha = 1F
            }
        }

        //displays all notes on the homepage again
        binding.allButton.setOnClickListener{
            viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(notesList)
                binding.notesRecyclerView.adapter = adapter
                //to change the transparency of the other categories when 'All' is clicked
                binding.allButton.alpha = 1F
                binding.generalButton.alpha = 0.4F
                binding.studyButton.alpha = 0.4F
                binding.workButton.alpha = 0.4F
                binding.personalButton.alpha = 0.4F
            }
        }

        //navigate to the create note page once the user clicks on the '+' icon
        binding.addNotesButton.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_fragmentHome_to_fragmentCreateNote)
        }
        return binding.root
    }

    //below functions are for when the user selects the search option in the menubar

    //function to create the options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_note, menu) //sets the search_note icon in the menu
        val item = menu.findItem(R.id.menuSearchIcon)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter notes here...." //hint displayed before user types anything

        //set a listener on the search text view
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                notesFiltering(p0?.lowercase()) //calls the notesFiltering function and changes p0 to lowercase
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }


    //function go create a list of the filtered notes and add them to the adapter
    private fun notesFiltering(p0: String?) {
        val filteredNotesList = arrayListOf<Notes>()
        //changed user input to lowercase so that the search query does not have to be case sensitive
        for (i in oldNotes){
            if (i.title.lowercase().contains(p0!!) || (i.subtitle.lowercase().contains(p0))){
                filteredNotesList.add(i)
            }
        }
        adapter.filterNotes(filteredNotesList) //calls the filterNotes function in the adapter
    }
}