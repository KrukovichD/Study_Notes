package com.geekbrains.study_notes.presentation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geekbrains.study_notes.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityRootBinding.inflate(layoutInflater)
        this.viewBinding = viewBinding
        setContentView(viewBinding.root)
    }
}