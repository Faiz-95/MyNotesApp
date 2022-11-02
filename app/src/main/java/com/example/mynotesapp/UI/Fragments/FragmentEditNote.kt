package com.example.mynotesapp.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mynotesapp.Model.Notes
import com.example.mynotesapp.R
import com.example.mynotesapp.ViewModel.NotesViewModel
import com.example.mynotesapp.databinding.FragmentEditNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Suppress("DEPRECATION")
class FragmentEditNote : Fragment() {

    val oldNotes by navArgs<FragmentEditNoteArgs>() //contains details of the note the user will edit
    lateinit var binding: FragmentEditNoteBinding
    val viewModel: NotesViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true) //true because we need to display the trash icon
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)

        binding.titleEditTextView.setText(oldNotes.data.title) //sets the title
        binding.subtitleEditTextView.setText(oldNotes.data.subtitle) //sets the subtitle
        binding.contentEditTextView.setText(oldNotes.data.content) //sets the content

        //setting the note color and dot based on the note the user had last saved
        when (oldNotes.data.color){
            "yellow" -> {
                binding.constraintLayoutEdit.setBackgroundResource(R.color.light_yellow)
                binding.yellowDotEdit.setImageResource(R.drawable.transparent_circle)
            }
            "pink" -> {
                binding.constraintLayoutEdit.setBackgroundResource(R.color.light_pink)
                binding.pinkDotEdit.setImageResource(R.drawable.transparent_circle)
            }
            "green" -> {
                binding.constraintLayoutEdit.setBackgroundResource(R.color.light_green)
                binding.greenDotEdit.setImageResource(R.drawable.transparent_circle)
            }
            "blue" -> {
                binding.constraintLayoutEdit.setBackgroundResource(R.color.light_blue)
                binding.blueDotEdit.setImageResource(R.drawable.transparent_circle)
            }
        }

        //I have set the outline of the color option selected to a transparent circle that has a black border

        //setting the background color of the note to yellow when the yellow dot is clicked
        binding.yellowDotEdit.setOnClickListener{
            oldNotes.data.color = "yellow"
            binding.constraintLayoutEdit.setBackgroundResource(R.color.light_yellow)
            binding.yellowDotEdit.setImageResource(R.drawable.transparent_circle)
            binding.pinkDotEdit.setImageResource(R.drawable.pink_dot)
            binding.greenDotEdit.setImageResource(R.drawable.green_dot)
            binding.blueDotEdit.setImageResource(R.drawable.blue_dot)
        }

        //setting the background color of the note to pink when the pink dot is clicked
        binding.pinkDotEdit.setOnClickListener{
            oldNotes.data.color = "pink"
            binding.constraintLayoutEdit.setBackgroundResource(R.color.light_pink)
            binding.pinkDotEdit.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotEdit.setImageResource(R.drawable.yellow_dot)
            binding.greenDotEdit.setImageResource(R.drawable.green_dot)
            binding.blueDotEdit.setImageResource(R.drawable.blue_dot)
        }

        //setting the background color of the note to green when the green dot is clicked
        binding.greenDotEdit.setOnClickListener{
            oldNotes.data.color = "green"
            binding.constraintLayoutEdit.setBackgroundResource(R.color.light_green)
            binding.greenDotEdit.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotEdit.setImageResource(R.drawable.yellow_dot)
            binding.pinkDotEdit.setImageResource(R.drawable.pink_dot)
            binding.blueDotEdit.setImageResource(R.drawable.blue_dot)
        }

        //setting the background color of the note to blue when the blue dot is clicked
        binding.blueDotEdit.setOnClickListener{
            oldNotes.data.color = "blue"
            binding.constraintLayoutEdit.setBackgroundResource(R.color.light_blue)
            binding.blueDotEdit.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotEdit.setImageResource(R.drawable.yellow_dot)
            binding.pinkDotEdit.setImageResource(R.drawable.pink_dot)
            binding.greenDotEdit.setImageResource(R.drawable.green_dot)
        }

        //function called when the save button is clicked
        binding.saveNotesButtonEdit.setOnClickListener{
            updateNotes(it)
        }

        return binding.root
    }

    //function to update a note when the save button is clicked
    private fun updateNotes(it: View?) {
        var title = binding.titleEditTextView.text.toString() //fetching the title typed by user
        //checks whether title is empty or sets the below string
        if (title.isEmpty()){
            title = "Unnamed Note"
        }
        val subtitle = binding.subtitleEditTextView.text.toString() //fetching the subtitle typed by user
        val content = binding.contentEditTextView.text.toString() //fetching the content typed by user

        val formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy") //formatting the date in my preferred format
        val currentDate = LocalDate.now().format(formatter) //gets the current date

        val note = Notes(
            oldNotes.data.id, //keep id the same as we only updating the note
            title,
            subtitle,
            content,
            currentDate.toString(),
            oldNotes.data.color
        ) //updates the note entry with the users input
        viewModel.updateNotes(note) //update the user's note in the View Model

        Toast.makeText(context, "Note successfully updated", Toast.LENGTH_SHORT).show() //create a toast
        Log.e("NOTE_ME", "Note successfully updated on $currentDate") //create a log entry

        //navigate to the homepage once the user saves the note
        Navigation.findNavController(it!!).navigate(R.id.action_fragmentEditNote_to_fragmentHome)
    }

    //below functions are for when the user selects the delete option in the menubar

    //function to create the options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_note, menu) //sets the delete_note icon in the menu
        super.onCreateOptionsMenu(menu, inflater)
    }

    //function when a menu item is selected (delete in this case)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuDeleteIcon){
            //setting a dialogue box to ask the user if they are sure they want to delete the note
            val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialogue_delete)

            val yesButton = bottomSheet.findViewById<TextView>(R.id.yesButton)
            val noButton = bottomSheet.findViewById<TextView>(R.id.noButton)

            //when yes button is clicked
            yesButton?.setOnClickListener{
                viewModel.deleteNotes(oldNotes.data.id!!) //deletes the user's note from the View Model using it's ID
                Toast.makeText(context, "Note successfully deleted", Toast.LENGTH_SHORT).show() //create a toast
                Log.e("NOTE_ME", "Note successfully deleted") //create a log entry
                bottomSheet.dismiss() //remove the dialogue box once user selects option
                findNavController().popBackStack() //navigate to the homepage once the user deletes the note
            }
            //when no button is clicked
            noButton?.setOnClickListener {
                bottomSheet.dismiss() //remove the dialogue box once user selects option
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}