package com.example.myapplication.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.model.DataProvider
import javax.inject.Inject

class AppVMFactory @Inject constructor(dp:DataProvider): ViewModelProvider.Factory {

    private val dataProvider = dp

    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(dataProvider) as T
        else
            throw IllegalArgumentException("ViewModel Not Found")

    }
}