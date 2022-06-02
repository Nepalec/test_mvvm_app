package com.example.myapplication.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import javax.inject.Inject

class DataProvider @Inject constructor(c: Context)
{
    private val context = c

    fun getUrls(links_url:String):LiveData<URLsResp> = run {
        val retVal = MutableLiveData<URLsResp>()
        val ur = URLsResp()
        val queue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(
            Request.Method.GET, links_url,
            { r ->
                ur.raw = r
                ur.initList()
                retVal.value = ur
            },
            {
                ur.isError=true
                ur.errMessage = it.message ?: "Ошибка получения данных по адресу ${links_url}: ${it.toString()}"
                retVal.value = ur
            })

        queue.add(stringRequest)

        retVal
    }
}