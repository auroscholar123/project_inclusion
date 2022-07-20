package com.auro.projectinclusion.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.auro.projectinclusion.Model.SendOtpModel
import com.auro.projectinclusion.R
import com.auro.projectinclusion.auth.AuthViewModel
import com.auro.projectinclusion.auth.OtpSendListener
import com.auro.projectinclusion.auth.VerifyOtpListener
import com.auro.projectinclusion.databinding.ActivityOtpVerificationBinding
import com.auro.projectinclusion.getData
import com.auro.projectinclusion.setAppLocale
import com.auro.projectinclusion.toast

class OtpVerificationActivity : AppCompatActivity(),VerifyOtpListener,OtpSendListener
{
    private var TAG = "OtpVerification"
    private lateinit var mBinding:ActivityOtpVerificationBinding
    private lateinit var mPhoneNumber:String
    private lateinit var mUserType:String
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.background_color)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_otp_verification)
        var viewmodel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.viewmodel = viewmodel
        viewmodel.mVerifyOtpListener = this
        viewmodel.mSendOtpListener = this
        mBinding.resendSmsLayout.isEnabled = false
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mBinding.otpTimerText.setText(""+ millisUntilFinished / 1000)

            }


            override fun onFinish() {
                mBinding.resendSmsLayout.isEnabled = true
                mBinding.resendSmsLayout.setBackgroundResource(R.drawable.blue_stroke_background)
                mBinding.resendTxt.setTextColor(Color.parseColor("#ff33b5e5"))

            }
        }.start()
        var lang_id:String = getData(this,"lang_id")
        if (lang_id.equals("2"))
        {
            setAppLocale(this,"hi")
            mBinding.otpHeaderTxt.setText(R.string.otp_verification)
            mBinding.otpHeader.setText(R.string.verification_msg)
            mBinding.resendTxt.setText(R.string.resend_sms)
            mBinding.callTxt.setText(R.string.call_txt)
            mBinding.otpContinue.setText(R.string.cont_text)
        }
        else
        {
            setAppLocale(this,"en")
            mBinding.otpHeaderTxt.setText(R.string.otp_verification)
            mBinding.otpHeader.setText(R.string.verification_msg)
            mBinding.resendTxt.setText(R.string.resend_sms)
            mBinding.callTxt.setText(R.string.call_txt)
            mBinding.otpContinue.setText(R.string.cont_text)
        }

        mUserType = intent.getStringExtra("usertype").toString()
        Log.d(TAG, "onCreateUserType: "+mUserType)
        mPhoneNumber = intent.getStringExtra("phone_no").toString()
        mBinding.numberTxt.setText("+91 "+mPhoneNumber)
        mBinding.verifyOtpButton.setOnClickListener {
         /*   if (viewmodel.edit_otp1.isNullOrEmpty())
            {
                toast("Enter OTP")
            }
            else if (viewmodel.edit_otp2.isNullOrEmpty())
            {
                toast("Enter OTP")
            }
            else if (viewmodel.edit_otp3.isNullOrEmpty())
            {
                toast("Enter OTP")
            }
            else if (viewmodel.edit_otp4.isNullOrEmpty())
            {
                toast("Enter OTP")
            }
            else if (viewmodel.edit_otp5.isNullOrEmpty())
            {
                toast("Enter OTP")
            }
            else if (viewmodel.edit_otp6.isNullOrEmpty())
            {
                toast("Enter OTP")
            }
            else
            {
                mBinding.verificationProgress.visibility = View.VISIBLE
                var otp = viewmodel.edit_otp1+viewmodel.edit_otp2+viewmodel.edit_otp3+viewmodel.edit_otp4+viewmodel.edit_otp5+viewmodel.edit_otp6
                viewmodel.verifyTheOtp(mPhoneNumber,otp)
            }*/

            startActivity(Intent(this,ResetPasswordActivity::class.java))


        }
        mBinding.resendSmsLayout.setOnClickListener {
            viewmodel.sendOtpMsg(mPhoneNumber)
            mBinding.resendSmsLayout.isEnabled = false
            mBinding.resendSmsLayout.setBackgroundResource(R.drawable.otp_back)
            mBinding.resendTxt.setTextColor(Color.parseColor("#808080"))
            object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    mBinding.otpTimerText.setText(""+ millisUntilFinished / 1000)

                }

                override fun onFinish() {
                    //   mBinding.otpTimerText.setText("done!")
                    mBinding.resendSmsLayout.isEnabled = true
                    mBinding.resendSmsLayout.setBackgroundResource(R.drawable.blue_stroke_background)
                    mBinding.resendTxt.setTextColor(Color.parseColor("#ff33b5e5"))
                }
            }.start()
        }
        mBinding.editFirstOtp.addTextChangedListener(object :TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               if (mBinding.editFirstOtp.text.length==1)
               {
                   mBinding.editSecondOtp.requestFocus()
               }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        mBinding.editSecondOtp.addTextChangedListener(object :TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (mBinding.editSecondOtp.text.length==1)
                {
                    mBinding.editThirdOtp.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        mBinding.editThirdOtp.addTextChangedListener(object :TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (mBinding.editThirdOtp.text.length==1)
                {
                    mBinding.editFourOtp.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        mBinding.editFourOtp.addTextChangedListener(object :TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (mBinding.editFourOtp.text.length==1)
                {
                    mBinding.editFiveOtp.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        mBinding.editFiveOtp.addTextChangedListener(object :TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (mBinding.editFiveOtp.text.length==1)
                {
                    mBinding.editSixOtp.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

       /* mBinding.relativeBack.setOnClickListener {
            finish()
        }*/

        Log.d(TAG, "onCreateDATA: "+TAG)

    }

    override fun onVerifyNetworkCall() {
        Log.d(TAG, "onVerifyNetworkCall: ")
    }

    override fun onVerifyOtpSuccess(verify_otp_response: MutableLiveData<SendOtpModel>)
    {
        Log.d(TAG, "onVerifyOtpSuccess: ")
        verify_otp_response.observe(this, Observer {
            mBinding.verificationProgress.visibility = View.GONE
            var msg = it.message.toString()
            if (msg.contentEquals("Success"))
            {
                toast("Otp Verified Successfully")
                finish()
                startActivity(Intent(this,SignUpActivity::class.java)
                    .putExtra("user_type",mUserType))
            }
            else
            {
                toast(msg)
            }
        })
    }

    override fun onVerifyOtpFail() {

    }

    override fun onOtpNetworkStart() {

    }

    override fun onOtpCallSuccess(otp_response: MutableLiveData<SendOtpModel>) {

    }

    override fun onOtpCallFail() {

    }


}