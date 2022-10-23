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

    val oldNotes by navArgs<FragmentEditNoteArgs>()
    lateinit var binding: FragmentEditNoteBinding
    val viewModel: NotesViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)

        binding.titleEditTextView.setText(oldNotes.data.title)
        binding.subtitleEditTextView.setText(oldNotes.data.subtitle)
        binding.contentEditTextView.setText(oldNotes.data.content)

        when (oldNotes.data.color){
            "yellow" -> {
                binding.constraintLayoutEdit.setBackgroundResource(R.color.light_yellow)
                binding.titleEditTextView.setBackgroundResource(R.color.light_yellow)
                binding.subtitleEditTextView.setBackgroundResource(R.color.light_yellow)
                binding.contentEditTextView.setBackgroundResource(R.color.light_yellow)
                binding.yellowDotEdit.setImageResource(R.drawable.transparent_circle)
            }
            "pink" -> {
                binding.constraintLayoutEdit.setBackgroundResource(R.color.light_pink)
                binding.titleEditTextView.setBackgroundResource(R.color.light_pink)
                binding.subtitleEditTextView.setBackgroundResource(R.color.light_pink)
                binding.contentEditTextView.setBackgroundResource(R.color.light_pink)
                binding.pinkDotEdit.setImageResource(R.drawable.transparent_circle)
            }
            "green" -> {
                binding.constraintLayoutEdit.setBackgroundResource(R.color.light_green)
                binding.titleEditTextView.setBackgroundResource(R.color.light_green)
                binding.subtitleEditTextView.setBackgroundResource(R.color.light_green)
                binding.contentEditTextView.setBackgroundResource(R.color.light_green)
                binding.greenDotEdit.setImageResource(R.drawable.transparent_circle)
            }
            "blue" -> {
                binding.constraintLayoutEdit.setBackgroundResource(R.color.light_blue)
                binding.titleEditTextView.setBackgroundResource(R.color.light_blue)
                binding.subtitleEditTextView.setBackgroundResource(R.color.light_blue)
                binding.contentEditTextView.setBackgroundResource(R.color.light_blue)
                binding.blueDotEdit.setImageResource(R.drawable.transparent_circle)
            }
        }

        binding.yellowDotEdit.setOnClickListener{
            oldNotes.data.color = "yellow"
            binding.constraintLayoutEdit.setBackgroundResource(R.color.light_yellow)
            binding.titleEditTextView.setBackgroundResource(R.color.light_yellow)
            binding.subtitleEditTextView.setBackgroundResource(R.color.light_yellow)
            binding.contentEditTextView.setBackgroundResource(R.color.light_yellow)
            binding.yellowDotEdit.setImageResource(R.drawable.transparent_circle)
            binding.pinkDotEdit.setImageResource(R.drawable.pink_dot)
            binding.greenDotEdit.setImageResource(R.drawable.green_dot)
            binding.blueDotEdit.setImageResource(R.drawable.blue_dot)
        }
        binding.pinkDotEdit.setOnClickListener{
            oldNotes.data.color = "pink"
            binding.constraintLayoutEdit.setBackgroundResource(R.color.light_pink)
            binding.titleEditTextView.setBackgroundResource(R.color.light_pink)
            binding.subtitleEditTextView.setBackgroundResource(R.color.light_pink)
            binding.contentEditTextView.setBackgroundResource(R.color.light_pink)
            binding.pinkDotEdit.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotEdit.setImageResource(R.drawable.yellow_dot)
            binding.greenDotEdit.setImageResource(R.drawable.green_dot)
            binding.blueDotEdit.setImageResource(R.drawable.blue_dot)
        }
        binding.greenDotEdit.setOnClickListener{
            oldNotes.data.color = "green"
            binding.constraintLayoutEdit.setBackgroundResource(R.color.light_green)
            binding.titleEditTextView.setBackgroundResource(R.color.light_green)
            binding.subtitleEditTextView.setBackgroundResource(R.color.light_green)
            binding.contentEditTextView.setBackgroundResource(R.color.light_green)
            binding.greenDotEdit.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotEdit.setImageResource(R.drawable.yellow_dot)
            binding.pinkDotEdit.setImageResource(R.drawable.pink_dot)
            binding.blueDotEdit.setImageResource(R.drawable.blue_dot)
        }
        binding.blueDotEdit.setOnClickListener{
            oldNotes.data.color = "blue"
            binding.constraintLayoutEdit.setBackgroundResource(R.color.light_blue)
            binding.titleEditTextView.setBackgroundResource(R.color.light_blue)
            binding.subtitleEditTextView.setBackgroundResource(R.color.light_blue)
            binding.contentEditTextView.setBackgroundResource(R.color.light_blue)
            binding.blueDotEdit.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotEdit.setImageResource(R.drawable.yellow_dot)
            binding.pinkDotEdit.setImageResource(R.drawable.pink_dot)
            binding.greenDotEdit.setImageResource(R.drawable.green_dot)
        }

        binding.saveNotesButtonEdit.setOnClickListener{
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.titleEditTextView.text.toString()
        val subtitle = binding.subtitleEditTextView.text.toString()
        val content = binding.contentEditTextView.text.toString()

        val formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy")
        val currentDate = LocalDate.now().format(formatter)

        // val notesDate: CharSequence = DateFormat.format("", date.time)

        val note = Notes(
            oldNotes.data.id,
            title,
            subtitle,
            content,
            currentDate.toString(),
            oldNotes.data.color
        )
        viewModel.updateNotes(note)

        Toast.makeText(context, "Note successfully updated", Toast.LENGTH_SHORT).show()
        Log.e("NOTE_ME", "Note successfully updated on $currentDate")

        Navigation.findNavController(it!!).navigate(R.id.action_fragmentEditNote_to_fragmentHome)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuDeleteIcon){
            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialogue_delete)

            val yesButton = bottomSheet.findViewById<TextView>(R.id.yesButton)
            val noButton = bottomSheet.findViewById<TextView>(R.id.noButton)

            yesButton?.setOnClickListener{
                viewModel.deleteNotes(oldNotes.data.id!!)
                Toast.makeText(context, "Note successfully deleted", Toast.LENGTH_SHORT).show()
                Log.e("NOTE_ME", "Note successfully deleted")
                bottomSheet.dismiss()
                findNavController().popBackStack()
            }
            noButton?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }


}