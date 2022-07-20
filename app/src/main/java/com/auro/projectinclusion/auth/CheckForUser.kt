package com.auro.projectinclusion.auth

import androidx.lifecycle.MutableLiveData
import com.auro.projectinclusion.Model.*

interface CheckForUser
{
    fun onNetworkcall()
    fun onUserSuccess(existingUserResponse: MutableLiveData<DuplicateUserModel>)
    fun onuserFalse()
}

interface FragAuthListener
{
    fun onNetworkFragStart()
    fun onFragSuccess(loginResponse: MutableLiveData<LoginModel>)
    fun onFragFail()
}

interface MyAuthListener
{
    fun onNetworkCallStarted()
    fun onApiSuccess(lang_response: MutableLiveData<List<LanguageModel>>)
    fun onApiFailure()
}
interface OtpSendListener
{
    fun onOtpNetworkStart()
    fun onOtpCallSuccess(otp_response: MutableLiveData<SendOtpModel>)
    fun onOtpCallFail()
}
interface ProfileAuthListener
{
    fun onProfileNetworkStarted()
    fun onProfileApiSuccess(profile_response: MutableLiveData<List<ProileModel>>)
    fun onProfileApiFailure()
}
interface SignUpAuthListener
{
    fun onSignUpNetwork()
    fun onSignUpSuccess(signup_response: MutableLiveData<SignUpModel>)
    fun onSignUpFailure()
}
interface VerifyOtpListener
{
    fun onVerifyNetworkCall()
    fun onVerifyOtpSuccess(verify_otp_response: MutableLiveData<SendOtpModel>)
    fun onVerifyOtpFail()
}
interface BottommenuListener
{
    fun onMenuCallStart()
    fun onMenuCallSuccess(menu_response: MutableLiveData<BottomMenuModel>)
    fun onMenuCallFail()
}