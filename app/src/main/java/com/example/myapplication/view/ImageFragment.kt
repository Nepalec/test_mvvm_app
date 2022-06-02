package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentImageBinding
import com.github.chrisbanes.photoview.OnPhotoTapListener

class ImageFragment  : Fragment()
{
    private lateinit var b: FragmentImageBinding
    val args: ImageFragmentArgs by navArgs()
    private var fs = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        b = FragmentImageBinding.inflate(inflater, container, false)
        Glide.with(requireContext()).load(args.url).into(b.img)
        b.img.setOnPhotoTapListener{ v,x,y -> imgTapped(v,x,y)}
        return b.root
    }

    private fun imgTapped(v: ImageView?, x: Float, y: Float) {
        fs=!fs
        (activity as MainActivity).enterFS(fs)
    }


}