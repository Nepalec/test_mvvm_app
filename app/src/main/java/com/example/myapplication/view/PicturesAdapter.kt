package com.example.myapplication.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.signature.ObjectKey
import com.example.myapplication.databinding.GridItemBinding


class PicturesAdapter(
    private val context:Context,
    private val items: MutableSet<String>,
    private var onClick: (String) -> Unit
) : RecyclerView.Adapter<PicturesAdapter.ImgViewHolder>()

{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImgViewHolder(GridItemBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun onBindViewHolder(h: ImgViewHolder, position: Int)
    {
        val url = items.elementAt(position)
        with(h.b)
        {
            val requestListener = object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    error.text = e?.message
                    error.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    error.visibility = View.GONE
                    return false
                }

            }
            root.setOnClickListener{ onClick(url)}

            val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(50,50)

            Glide.with(context)
                .load(url)
                .listener(requestListener)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(requestOptions)
                .centerCrop()
                .into(img);

        }
    }

    override fun getItemCount() = items.size

    class ImgViewHolder(_b: GridItemBinding) : RecyclerView.ViewHolder(_b.root) {
        val b = _b
    }



}
