package com.auro.projectinclusion.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.auro.projectinclusion.*
import com.auro.projectinclusion.Activity.DashBoardActivity
import com.auro.projectinclusion.Activity.OtpVerificationActivity
import com.auro.projectinclusion.Model.LoginModel
import com.auro.projectinclusion.auth.AuthViewModel
import com.auro.projectinclusion.auth.FragAuthListener
import com.auro.projectinclusion.databinding.FragmentPasswordBinding


class PasswordFragment : Fragment(), FragAuthListener {

    private var TAG = "PasswordFragment"
    private lateinit var mBinding:FragmentPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_password, container, false)
        val view = mBinding.root
        
        val viewmodel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.viewmodel = viewmodel
        viewmodel.mFragListener = this
        var bundle = arguments
        var phone = bundle!!.getString("phone").toString()
        var user_type = bundle!!.getString("usertype").toString()
        Log.d(TAG, "onCreateView: "+phone)

        mBinding.clickHereText.setOnClickListener {
            context?.startActivity(Intent(context!!,OtpVerificationActivity::class.java))

        mBinding.hidePassImg.setOnClickListener {
            mBinding.editPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            mBinding.showPassImg.visibility = View.VISIBLE
            mBinding.hidePassImg.visibility = View.GONE
        }
        mBinding.showPassImg.setOnClickListener {
            mBinding.editPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            mBinding.showPassImg.visibility = View.GONE
            mBinding.hidePassImg.visibility = View.VISIBLE
        }
        var lang_id:String = activity!!.getData(activity!!,"lang_id")
        if (lang_id.equals("2"))
        {
            activity!!.setAppLocale(activity!!,"hi")
            mBinding.selectTextPswd.setText(R.string.password_header)
            mBinding.continuePswrdBttn.setText(R.string.cont_text)
        }
        else
        {
            activity!!.setAppLocale(activity!!,"en")
            mBinding.selectTextPswd.setText(R.string.password_header)
            mBinding.continuePswrdBttn.setText(R.string.cont_text)
        }
        
        mBinding.loginPasswordButton.setOnClickListener {
            var pswd = mBinding.editPassword.text.toString().trim()
            if (pswd.isNullOrEmpty())
            {
                mBinding.editPassword.setError("Enter Password")
                mBinding.editPassword.requestFocus()
            }
            else
            {
                mBinding.passwordProgress.visibility = View.VISIBLE
                viewmodel.getLogin(phone,pswd,user_type)
            }
        }


        }
        return view
    }

    override fun onNetworkFragStart() {
        Log.d(TAG, "onNetworkFragStart: ")
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onFragSuccess(loginResponse: MutableLiveData<LoginModel>) {
        Log.d(TAG, "onFragSuccess: ")
        loginResponse.observe(this, Observer {
            var status = it.status.toString()
            var msg = it.message.toString()

            Log.d(TAG, "onFragSuccess: "+"status"+status+ " "+"msg "+msg)
            if (status.equals("1")||msg.contentEquals("Success"))
            {
                var token = it.token.toString()
                activity?.saveData(activity!!,"user_token",token)
                activity?.toast("Login Successfully")
                mBinding.passwordProgress.visibility = View.GONE
                val i = Intent(activity, DashBoardActivity::class.java)
                startActivity(i)
            }
            else
            {
                activity?.toast(msg)
            }
        })
    }

    override fun onFragFail() {
        Log.d(TAG, "onFragFail: ")
    }









}