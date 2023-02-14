package com.geekbrains.study_notes.presentation


import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import com.geekbrains.study_notes.databinding.ActivityRootBinding
import com.geekbrains.study_notes.presentation.details.NoteDetailsFragment
import com.geekbrains.study_notes.presentation.list.NoteListFragment

class RootActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityRootBinding.inflate(layoutInflater)
        this.viewBinding = viewBinding
        setContentView(viewBinding.root)
        initFragment()

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (supportFragmentManager.backStackEntryCount > 0) {
                        Log.d("fds", supportFragmentManager.backStackEntryCount.toString())
                        supportFragmentManager.popBackStack()
                    } else {
                        initFragment()
                    }
                }
            })
    }

    private fun isLandscape(): Boolean {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return true
        }
        return false
    }

    private fun initFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                viewBinding.container.id,
                NoteListFragment()
            )
            .commit()

        if (isLandscape())
            supportFragmentManager
                .beginTransaction()
                .replace(
                    viewBinding.containerDetail!!.id,
                    NoteDetailsFragment.newInstance()
                )
                .commit()
    }
}