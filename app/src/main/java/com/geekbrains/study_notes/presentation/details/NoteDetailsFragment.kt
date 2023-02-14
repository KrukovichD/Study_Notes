package com.geekbrains.study_notes.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.geekbrains.study_notes.databinding.FragmentNoteDetailsBinding
import com.geekbrains.study_notes.presentation.list.NoteListViewModel

class NoteDetailsFragment : Fragment() {
    private lateinit var _binding: FragmentNoteDetailsBinding
    private val viewModel: NoteListViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNoteDetailsBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel._note.observe(viewLifecycleOwner) {
            _binding.textViewTitle.text = it.title
            _binding.textViewContent.text = it.content
            _binding.textViewDate.text = it.date
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            NoteDetailsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}