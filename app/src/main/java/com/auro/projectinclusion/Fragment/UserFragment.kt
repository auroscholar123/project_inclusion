package com.auro.projectinclusion.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.auro.projectinclusion.Activity.LanguageActivity
import com.auro.projectinclusion.Activity.ResetPasswordActivity
import com.auro.projectinclusion.R
import com.auro.projectinclusion.auth.AuthViewModel
import com.auro.projectinclusion.databinding.FragmentEmailBinding
import com.auro.projectinclusion.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private lateinit var mBinding: FragmentUserBinding
    private lateinit var mViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_user,container,false)
        val view = mBinding.root
        mViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.viewmodel = mViewModel

        mBinding.profileDetailLayout.setOnClickListener {
            fragmentManager?.beginTransaction()!!.replace(R.id.fragment_container,ProfileDetailFragment()).commit()
        }

        mBinding.languageLayout.setOnClickListener {
            startActivity(Intent(context,LanguageActivity::class.java))
        }

        mBinding.changePswdLayout.setOnClickListener {
            startActivity(Intent(context,ResetPasswordActivity::class.java))
        }

        return view

    }


}