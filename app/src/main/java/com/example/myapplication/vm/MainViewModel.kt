package com.example.myapplication.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.DataProvider
import com.example.myapplication.model.URLsResp

class MainViewModel(dp:DataProvider) : ViewModel()
{
    val dataProvider = dp

    val data: LiveData<URLsResp> by lazy {
        dataProvider.getUrls("https://it-link.ru/test/images.txt")
    }
}