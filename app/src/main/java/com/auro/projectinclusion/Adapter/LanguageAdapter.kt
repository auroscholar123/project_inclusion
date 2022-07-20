package com.auro.projectinclusion.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.auro.projectinclusion.Interface.GetLanguageID
import com.auro.projectinclusion.Model.LanguageModel
import com.auro.projectinclusion.R

class LanguageAdapter(var mContext:Context,var mLangList:ArrayList<LanguageModel>,var getLang:GetLanguageID) : RecyclerView.Adapter<LanguageAdapter.LanguageHolder>()
{
    private var selectedPosition  = -1
    class LanguageHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var lang_name:TextView = itemView.findViewById(R.id.language_txt)
        var lang_layout:RelativeLayout = itemView.findViewById(R.id.lang_layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageHolder
    {
       val view = LayoutInflater.from(mContext).inflate(R.layout.language_recycler_item,parent,false)
       return LanguageHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: LanguageHolder, position: Int)
    {
        var lang_model = mLangList.get(position)
        holder.lang_name.setText(lang_model.name)


        holder.lang_layout.setOnClickListener {
            Log.d("adappos", holder.adapterPosition.toString())
            notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            holder.lang_layout.setBackgroundResource(R.drawable.background_blue)
            holder.lang_name.setTextColor(Color.parseColor("#FFFFFFFF"))
            getLang.getLangId(lang_model.id)

        }
        if (selectedPosition==holder.adapterPosition)
        {
            holder.lang_layout.setBackgroundResource(R.drawable.background_blue)
            holder.lang_name.setTextColor(Color.parseColor("#FFFFFFFF"))

        }
        else
        {
            holder.lang_layout.setBackgroundResource(R.drawable.language_background)
            holder.lang_name.setTextColor(Color.parseColor("#6E6E6E"))
            Log.d("called", "onBindViewHolder: ")
        }
    }

    override fun getItemCount(): Int
    {
        return mLangList.size
    }
}