package com.geekbrains.study_notes.presentation.activity


import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.geekbrains.study_notes.R
import com.geekbrains.study_notes.databinding.ActivityRootBinding
import com.geekbrains.study_notes.presentation.AboutDialogFragment
import com.geekbrains.study_notes.presentation.details.NoteDetailsFragment
import com.geekbrains.study_notes.presentation.list.NoteListFragment

class RootActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewBinding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityRootBinding.inflate(layoutInflater)
        this.viewBinding = viewBinding
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.appBarMain!!.toolbar)
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

        viewBinding.navView?.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.about -> {
                    AboutDialogFragment().show(supportFragmentManager, "About")
                    return@setNavigationItemSelectedListener true
                }
                R.id.exit -> {
                    finish()
                    return@setNavigationItemSelectedListener true
                }
                else -> return@setNavigationItemSelectedListener false

            }
        }
    }

    private fun isLandscape(): Boolean =
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    private fun initFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                viewBinding.appBarMain.content.container.id,
                NoteListFragment()
            )
            .commit()

        if (isLandscape())
            supportFragmentManager
                .beginTransaction()
                .replace(
                    viewBinding.appBarMain.content.containerDetail!!.id,
                    NoteDetailsFragment.newInstance()
                )
                .commit()
    }


}