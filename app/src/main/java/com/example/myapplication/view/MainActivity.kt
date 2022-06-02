package com.example.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.myapplication.R

class MainActivity : AppCompatActivity()
{
    private lateinit var _nav:NavController
    val nav get() = _nav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        _nav = findNavController(this, R.id.content)
    }

}