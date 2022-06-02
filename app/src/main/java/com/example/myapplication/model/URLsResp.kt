package com.example.myapplication.model

import java.io.BufferedReader
import java.io.StringReader

class URLsResp
{
    var raw:String =""
    val urls: MutableSet<String> = mutableSetOf<String>()
    var isError=false
    var errMessage:String? = ""

    fun initList() {
        val lines = BufferedReader(StringReader(raw)).lines()
        lines.forEach { urls.add(it) }
    }

}