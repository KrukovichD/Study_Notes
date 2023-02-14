package com.geekbrains.study_notes.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.study_notes.data.NoteListItem

class NoteListViewModel : ViewModel() {
    private var list: ArrayList<NoteListItem> = ArrayList()

    private val _notes = MutableLiveData<List<NoteListItem>?>()
    val _note: MutableLiveData<NoteListItem> = MutableLiveData()

    val notes: LiveData<List<NoteListItem>?> = _notes
    //val note: LiveData<NoteListItem> = _note

    fun getList() {
        _notes.postValue(getListNoteListitem())
    }

    fun getNoteById(id: Int) {
        if (list.size == 0)
            getListNoteListitem()
        for (noteFL in list) {
            if (noteFL.id == id) {
                _note.postValue(noteFL)
                return
            }
        }
        _note.postValue(list[0])
    }

    fun insertNotetoDatabase() {}

    fun deledeNoteFromDatabase() {}


    private fun getNoteListItem(counter: Int): NoteListItem =
        NoteListItem(
            id = counter,
            title = "title $counter",
            content = "content $counter",
            date = "0$counter.02.23"
        )

    private fun getListNoteListitem(): List<NoteListItem> {
        list = ArrayList()
        for (i in 0..5) {
            list.add(getNoteListItem(i))
        }
        return list
    }

}
