package com.geekbrains.study_notes.presentation.list

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.study_notes.R
import com.geekbrains.study_notes.data.NoteListItem
import com.geekbrains.study_notes.databinding.FragmentNoteListBinding
import com.geekbrains.study_notes.databinding.ListItemNoteBinding
import com.geekbrains.study_notes.presentation.details.NoteDetailsFragment

class NoteListFragment : Fragment() {
    private var _binding: FragmentNoteListBinding? = null

    private val viewModel: NoteListViewModel by activityViewModels()

    private val recyclerViewAdapter = RecyclerViewAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)

        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("fs", "onViewCreated")
        _binding!!.list.adapter = recyclerViewAdapter

        viewModel.getList()


        recyclerViewAdapter.onItemClickListener = {
            Log.d("fs", "onClick")
            viewModel.getNoteById(it.id)
            launchNoteDetailsFragment()
        }

        viewModel.notes.observe(viewLifecycleOwner
        ) {
            if (it != null) {
                recyclerViewAdapter.setItems(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchNoteDetailsFragment() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container_detail, NoteDetailsFragment.newInstance())
                .addToBackStack(null)
                .commit()
        } else {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, NoteDetailsFragment.newInstance())
                .commit()
        }
    }

    companion object
}

private class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val items = mutableListOf<NoteListItem>()
    var onItemClickListener: ((NoteListItem) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        ListItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.binding.linearLayout.setOnClickListener {
            onItemClickListener?.invoke(items[position])
        }
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(
        items: List<NoteListItem>
    ) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(
        val binding: ListItemNoteBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(
            note: NoteListItem
        ) = with(binding) {
            titleLabel.text = note.title
            dateLabel.text = note.date
        }

    }
}