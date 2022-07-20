package com.auro.projectinclusion.Adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.auro.projectinclusion.ClickProfileId
import com.auro.projectinclusion.Model.ProileModel
import com.auro.projectinclusion.R
import com.bumptech.glide.Glide
import okhttp3.internal.Util


class ChooseProfileAdapter(var mcontext:Context,var mProfileList:ArrayList<ProileModel>,var profileId:ClickProfileId): RecyclerView.Adapter<ChooseProfileAdapter.ChooseProfileHolder>()
{
    private var selectedPosition  = -1
    class ChooseProfileHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var profile_name:TextView = itemView.findViewById(R.id.profile_name)
        var profile_layout:RelativeLayout = itemView.findViewById(R.id.profile_layout)
        var profile_image:ImageView = itemView.findViewById(R.id.profile_img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseProfileHolder
    {
        val view = LayoutInflater.from(mcontext).inflate(R.layout.profile_recycler_item,parent,false)
        return ChooseProfileHolder(view)
    }

    override fun onBindViewHolder(holder: ChooseProfileHolder, position: Int)
    {
        var profile_model = mProfileList.get(position)
        holder.profile_name.setText(profile_model.name)
        holder.profile_layout.setOnClickListener {
            notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            profileId.clickToGetProfileId(profile_model.id!!)
            holder.profile_layout.setBackgroundResource(R.drawable.select_circle_background)
        }
        if (selectedPosition==holder.adapterPosition)
        {
            holder.profile_layout.setBackgroundResource(R.drawable.background_blue)


        }
        else
        {
            holder.profile_layout.setBackgroundResource(R.drawable.language_background)

            Log.d("called", "onBindViewHolder: ")
        }
        holder.profile_image.loadImageFromUrl(profile_model.icon!!)

    }

    fun ImageView.loadImageFromUrl(imageUrl: String) {
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadImageFromUrl.context))
            }
            .build()

        val imageRequest = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(300)
            .data(imageUrl)
            .target(
                onStart = {
                    //set up an image loader or whatever you need
                },
                onSuccess = { result ->
                    val bitmap = (result as BitmapDrawable).bitmap
                    this.setImageBitmap(bitmap)
                    //dismiss the loader if any
                },
                onError = {
                    /**
                     * TODO: set an error drawable
                     */
                }
            )
            .build()

        imageLoader.enqueue(imageRequest)

        //   Glide.with(mcontext).load(profile_model.icon).into(holder.profile_image)
        //   Picasso.get().load(profile_model.icon).into(holder.profile_image)
        /*GlideT
            .init()
            .with(this.context)
            .setPlaceHolder(R.drawable.loading, R.drawable.actual)
            .load(Uri.parse(url), this)*/
    }


    override fun getItemCount(): Int
    {
        return mProfileList.size
    }
}