package com.example.mynotesapp.UI.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.mynotesapp.Model.Notes
import com.example.mynotesapp.R
import com.example.mynotesapp.ViewModel.NotesViewModel
import com.example.mynotesapp.databinding.FragmentCreateNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Suppress("DEPRECATION")
class FragmentCreateNote : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    var color: String = "yellow"
    val viewModel: NotesViewModel by viewModels()
    var noteID: Int = 0

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)
        binding.yellowDotCreate.setImageResource(R.drawable.transparent_circle)

        binding.yellowDotCreate.setOnClickListener{
            color = "yellow"
            binding.constraintLayoutCreate.setBackgroundResource(R.color.light_yellow)
            binding.titleCreateTextView.setBackgroundResource(R.color.light_yellow)
            binding.subtitleCreateTextView.setBackgroundResource(R.color.light_yellow)
            binding.contentCreateTextView.setBackgroundResource(R.color.light_yellow)
            binding.yellowDotCreate.setImageResource(R.drawable.transparent_circle)
            binding.pinkDotCreate.setImageResource(R.drawable.pink_dot)
            binding.greenDotCreate.setImageResource(R.drawable.green_dot)
            binding.blueDotCreate.setImageResource(R.drawable.blue_dot)
        }
        binding.pinkDotCreate.setOnClickListener{
            color = "pink"
            binding.constraintLayoutCreate.setBackgroundResource(R.color.light_pink)
            binding.titleCreateTextView.setBackgroundResource(R.color.light_pink)
            binding.subtitleCreateTextView.setBackgroundResource(R.color.light_pink)
            binding.contentCreateTextView.setBackgroundResource(R.color.light_pink)
            binding.pinkDotCreate.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotCreate.setImageResource(R.drawable.yellow_dot)
            binding.greenDotCreate.setImageResource(R.drawable.green_dot)
            binding.blueDotCreate.setImageResource(R.drawable.blue_dot)
        }
        binding.greenDotCreate.setOnClickListener{
            color = "green"
            binding.constraintLayoutCreate.setBackgroundResource(R.color.light_green)
            binding.titleCreateTextView.setBackgroundResource(R.color.light_green)
            binding.subtitleCreateTextView.setBackgroundResource(R.color.light_green)
            binding.contentCreateTextView.setBackgroundResource(R.color.light_green)
            binding.greenDotCreate.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotCreate.setImageResource(R.drawable.yellow_dot)
            binding.pinkDotCreate.setImageResource(R.drawable.pink_dot)
            binding.blueDotCreate.setImageResource(R.drawable.blue_dot)
        }
        binding.blueDotCreate.setOnClickListener{
            color = "blue"
            binding.constraintLayoutCreate.setBackgroundResource(R.color.light_blue)
            binding.titleCreateTextView.setBackgroundResource(R.color.light_blue)
            binding.subtitleCreateTextView.setBackgroundResource(R.color.light_blue)
            binding.contentCreateTextView.setBackgroundResource(R.color.light_blue)
            binding.blueDotCreate.setImageResource(R.drawable.transparent_circle)
            binding.yellowDotCreate.setImageResource(R.drawable.yellow_dot)
            binding.pinkDotCreate.setImageResource(R.drawable.pink_dot)
            binding.greenDotCreate.setImageResource(R.drawable.green_dot)
        }

        binding.saveNotesButtonCreate.setOnClickListener{
            createNotes(it)
        }

        return binding.root
    }


    private fun createNotes(it: View?) {
        val title = binding.titleCreateTextView.text.toString()
        val subtitle = binding.subtitleCreateTextView.text.toString()
        val content = binding.contentCreateTextView.text.toString()

        val formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy")
        val currentDate = LocalDate.now().format(formatter)

        val note = Notes(
            null,
            title,
            subtitle,
            content,
            currentDate.toString(),
            color
        )
        viewModel.insertNotes(note)

        Toast.makeText(context, "Note successfully created", Toast.LENGTH_SHORT).show()
        Log.e("NOTE_ME", "Note successfully created on $currentDate")

        Navigation.findNavController(it!!).navigate(R.id.action_fragmentCreateNote_to_fragmentHome)
    }
}