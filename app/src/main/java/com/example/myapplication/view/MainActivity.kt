package com.example.myapplication.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.utils.*

class MainActivity : AppCompatActivity()
{
    private lateinit var _nav:NavController
    val nav get() = _nav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        _nav = findNavController(this, R.id.content)
    }

    fun enterFS(bFS: Boolean) {  if(bFS) hideSystemUI() else showSystemUI()   }

    fun nightModeSwitch() {
        prefs?.night_mode  = !prefs?.night_mode!!
        applyNM()
    }

    override fun onResume() {
        super.onResume()
        applyNM()
    }
    fun applyNM(){
        AppCompatDelegate.setDefaultNightMode(if(prefs?.night_mode!!)  MODE_NIGHT_YES else MODE_NIGHT_NO);
    }

}