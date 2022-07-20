package com.auro.projectinclusion.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.auro.projectinclusion.Fragment.EmailFragment
import com.auro.projectinclusion.Fragment.PasswordFragment
import com.auro.projectinclusion.R

class LoginActivity : AppCompatActivity()
{
    private var TAG = "LoginActivity"
    public lateinit var mUserTypeId:String
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.background_color)
        mUserTypeId = intent.getStringExtra("userTypeId").toString()
        Log.d(TAG, "onCreate: "+mUserTypeId)
            loadFragment(EmailFragment())

    }


     fun loadFragment(fragment: Fragment)
    {
        var fragmanager = supportFragmentManager
        var frag_transaction = fragmanager.beginTransaction()
        frag_transaction.add(R.id.frame_frag_container,fragment).commit()
    }



}