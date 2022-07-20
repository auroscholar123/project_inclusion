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
import com.auro.projectinclusion.Activity.LmsCertificateActivity
import com.auro.projectinclusion.R
import com.auro.projectinclusion.auth.AuthViewModel
import com.auro.projectinclusion.databinding.FragmentCertificateBinding


class CertificateFragment : Fragment() {

    private lateinit var mBinding:FragmentCertificateBinding
    private lateinit var mViewModel:AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_certificate, container, false)
        val view = mBinding.root
        mViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.viewmodel = mViewModel

        mBinding.lmsLayout.setOnClickListener {
            startActivity(Intent(context!!,LmsCertificateActivity::class.java))
        }

        return view
    }


}