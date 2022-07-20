package com.auro.projectinclusion.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.auro.projectinclusion.Fragment.UserFragment
import com.auro.projectinclusion.R

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        loadFragment(UserFragment())
    }

    fun loadFragment(fragment: Fragment)
    {
        var fragmanager = supportFragmentManager
        var frag_transaction = fragmanager.beginTransaction()
        frag_transaction.add(R.id.fragment_container,fragment).commit()
    }
}