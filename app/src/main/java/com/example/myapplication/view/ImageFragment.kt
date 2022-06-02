package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentImageBinding
import com.example.myapplication.databinding.FragmentMainBinding

class ImageFragment  : Fragment()
{
    private lateinit var b: FragmentImageBinding
    val args: ImageFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        b = FragmentImageBinding.inflate(inflater, container, false)
        Glide.with(requireContext()).load(args.url).into(b.img)
        return b.root
    }
}