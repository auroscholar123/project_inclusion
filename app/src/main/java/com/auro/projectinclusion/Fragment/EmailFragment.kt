package com.auro.projectinclusion.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.auro.projectinclusion.Activity.LoginActivity
import com.auro.projectinclusion.Activity.OtpVerificationActivity
import com.auro.projectinclusion.Model.DuplicateUserModel
import com.auro.projectinclusion.Model.SendOtpModel
import com.auro.projectinclusion.R
import com.auro.projectinclusion.auth.AuthViewModel
import com.auro.projectinclusion.auth.CheckForUser
import com.auro.projectinclusion.auth.OtpSendListener
import com.auro.projectinclusion.databinding.FragmentEmailBinding
import com.auro.projectinclusion.getData
import com.auro.projectinclusion.setAppLocale
import com.auro.projectinclusion.toast


class EmailFragment : Fragment(),CheckForUser,OtpSendListener
{
    private var TAG = "EmailFragment"
    private lateinit var mEditPhone:EditText
    private var mUsername:String = ""
    private lateinit var mPhoneLoginButton:RelativeLayout
    private lateinit var loginActivity:LoginActivity
    private lateinit var mBinding:FragmentEmailBinding
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

        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_email,container,false)
        val view = mBinding.root
        mViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.viewmodel = mViewModel
        mViewModel.mCheckUserListener = this
        mViewModel.mSendOtpListener = this
        loginActivity = (activity as LoginActivity)
        var lang_id:String = activity!!.getData(activity!!,"lang_id")
        if (lang_id.equals("2"))
        {
            activity!!.setAppLocale(activity!!,"hi")
            mBinding.selectText.setText(R.string.log_signup_txt)
            mBinding.logBttnTxt.setText(R.string.cont_text)
        }
        else
        {
            activity!!.setAppLocale(activity!!,"en")
            mBinding.selectText.setText(R.string.log_signup_txt)
            mBinding.logBttnTxt.setText(R.string.cont_text)
        }
        mBinding.loginPhoneButton.setOnClickListener {

            if (mViewModel.email_login.isNullOrEmpty())
            {
                mBinding.editPhone.setError("Enter Valid Username")
                mBinding.editPhone.requestFocus()
            }
            else
            {
                mUsername = mViewModel.email_login!!.trim().toString()
                mBinding.emailProgress.visibility = View.VISIBLE
                mViewModel.CheckforExistingUser(mUsername, (activity as LoginActivity).mUserTypeId)


            }

        }

        return view
    }

    override fun onNetworkcall() {
        Log.d(TAG, "onNetworkcall: ")
    }

    override fun onUserSuccess(existingUserResponse: MutableLiveData<DuplicateUserModel>)
    {
        Log.d(TAG, "onUserSuccess: ")
        existingUserResponse.observe(this, Observer {
            var isDuplicateUser = it.isDuplicateUser.toString()
            var message = it.message.toString()
            if (isDuplicateUser.equals("0"))
            {
                mBinding.emailProgress.visibility = View.GONE
                mViewModel.sendOtpMsg(mUsername)
             //   activity?.toast(message)
                val intent = Intent(activity, OtpVerificationActivity::class.java)
                intent.putExtra("phone_no",mUsername)
                intent.putExtra("usertype",(activity as LoginActivity).mUserTypeId)
                startActivity(intent)

            }
            else
            {
                mBinding.emailProgress.visibility = View.GONE
          //      activity?.toast(message)
                var pswd_frag = PasswordFragment()
                var bundle = Bundle()
                bundle.putString("phone",mBinding.editPhone.text.toString())
                bundle.putString("usertype",(activity as LoginActivity).mUserTypeId)
                pswd_frag.arguments = bundle
                fragmentManager?.beginTransaction()!!.replace(R.id.frame_frag_container,pswd_frag,"passwordfrag").addToBackStack(null).commit()
            }
        })
    }

    override fun onuserFalse() {
        Log.d(TAG, "onuserFalse: ")
    }

    override fun onOtpNetworkStart() {
        Log.d(TAG, "onOtpNetworkStart: ")
    }

    override fun onOtpCallSuccess(otp_response: MutableLiveData<SendOtpModel>) {
        Log.d(TAG, "onOtpCallSuccess: ")
        otp_response.observe(this, Observer {

        })
    }

    override fun onOtpCallFail() {
        Log.d(TAG, "onOtpCallFail: ")
    }


}