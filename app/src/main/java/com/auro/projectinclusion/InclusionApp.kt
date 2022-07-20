package com.auro.projectinclusion

import android.app.Application

class InclusionApp : Application()
{
    companion object
    {
        public lateinit var mContext:InclusionApp
    }
    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}