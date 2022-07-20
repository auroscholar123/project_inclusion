package com.auro.projectinclusion.auth

import androidx.lifecycle.ViewModel
import com.auro.projectinclusion.Repository.AuroRepository

class AuthViewModel : ViewModel()
{

   var mAuthListener:MyAuthListener? = null
   var mProAuthListener:ProfileAuthListener? = null
   var mFragListener:FragAuthListener? = null
   var mSignUpAuthListener:SignUpAuthListener? = null
   var mCheckUserListener:CheckForUser? = null
   var mSendOtpListener:OtpSendListener? = null
   var mVerifyOtpListener:VerifyOtpListener? = null
   var mBottommenuListener:BottommenuListener? = null

   var email_login:String? = null
   var name_signup:String? = null
   var username_signup:String? = null
   var password_signup:String? = null
   var edit_otp1:String? = null
    var edit_otp2:String? = null
    var edit_otp3:String? = null
    var edit_otp4:String? = null
    var edit_otp5:String? = null
    var edit_otp6:String? = null


  fun getLanguageData()
  {
    var lang_response = AuroRepository().getLanguageList()
    mAuthListener!!.onNetworkCallStarted()
    mAuthListener!!.onApiSuccess(lang_response)
  }

  fun getProfileData()
  {
    var profile_response = AuroRepository().getProfileList()
    mProAuthListener!!.onProfileNetworkStarted()
    mProAuthListener!!.onProfileApiSuccess(profile_response)
    mProAuthListener!!.onProfileApiFailure()
  }

  fun getLogin(username:String,password:String,userType:String)
  {
    var loginResponse = AuroRepository().completeLogin(username,password,userType)
    mFragListener!!.onNetworkFragStart()
    mFragListener!!.onFragSuccess(loginResponse)
  }

    fun CheckforExistingUser(username: String,userType: String)
    {
        var existingUserResponse = AuroRepository().checkUser(username,userType)
        mCheckUserListener?.onNetworkcall()
        mCheckUserListener?.onUserSuccess(existingUserResponse)
    }

    fun sendOtpMsg(mobile:String)
    {
        var otp_response = AuroRepository().sendOtpFromMobile(mobile)
        mSendOtpListener?.onOtpNetworkStart()
        mSendOtpListener?.onOtpCallSuccess(otp_response)
    }

    fun verifyTheOtp(phone:String,otp:String)
    {
        var verify_otp_response = AuroRepository().onVerifyOtp(phone,otp)
        mVerifyOtpListener?.onVerifyNetworkCall()
        mVerifyOtpListener?.onVerifyOtpSuccess(verify_otp_response)
    }

    fun signUpUser(username: String,userType: String,password: String)
    {
      var signup_response = AuroRepository().signup(username,userType,password)
      mSignUpAuthListener!!.onSignUpNetwork()
      mSignUpAuthListener!!.onSignUpSuccess(signup_response)
    }

    fun getMenu()
    {
        var menu_response = AuroRepository().getBottomMenu()
        mBottommenuListener?.onMenuCallStart()
        mBottommenuListener?.onMenuCallSuccess(menu_response)
    }


}