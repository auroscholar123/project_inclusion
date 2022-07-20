package com.auro.projectinclusion.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.auro.openactivity.OpenInclusion
import com.auro.projectinclusion.Activity.SplashActivity
import com.auro.projectinclusion.Activity.UserProfileActivity
import com.auro.projectinclusion.R
import com.auro.projectinclusion.auth.AuthViewModel
import com.auro.projectinclusion.databinding.FragmentCoursesBinding
import com.auro.projectinclusion.databinding.FragmentEmailBinding

class CoursesFragment : Fragment() {

    private lateinit var mBinding: FragmentCoursesBinding
    private lateinit var mViewModel:AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_courses,container,false)
        val view = mBinding.root
        mViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.viewmodel = mViewModel

      //  OpenInclusion.opensplash(context!!, SplashActivity())

        mBinding.imgProfileLayout.setOnClickListener {

            startActivity(Intent(context!!,UserProfileActivity::class.java))
        }
        return mBinding.root
    }


}