package com.auro.projectinclusion.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.auro.projectinclusion.Model.*
import com.auro.projectinclusion.Url.ServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuroRepository
{
    var TAG = "AuroRepository"
    var phone:String? = null
    var pswd:String? = null
    fun getLanguageList(): MutableLiveData<List<LanguageModel>>
    {
        var responseData = MutableLiveData<List<LanguageModel>>()
        ServiceApi().getLanguage()
            .enqueue(object : Callback<List<LanguageModel>>
            {
                override fun onResponse(
                    call: Call<List<LanguageModel>>,
                    response: Response<List<LanguageModel>>
                )
                {
                    if (response.isSuccessful)
                    {

                      responseData.value = response.body()
                       Log.d("responseList", response.body()!!.size.toString())

                    }
                    else
                    {
                      //  responseData.value = response.errorBody().toString()
                        Log.d("response", "onResponse: ")
                    }
                }

                override fun onFailure(call: Call<List<LanguageModel>>, t: Throwable)
                {
               //     responseData.value = t.message
                    Log.d("justfail", t.message.toString())
                }

            })

        return responseData
    }

    fun getProfileList():MutableLiveData<List<ProileModel>>
    {
        var profile_response = MutableLiveData<List<ProileModel>>()
        ServiceApi().getProfile()
            .enqueue(object : Callback<List<ProileModel>>
            {
                override fun onResponse(
                    call: Call<List<ProileModel>>,
                    response: Response<List<ProileModel>>
                ) {
                    if (response.isSuccessful)
                    {
                        profile_response.value = response.body()
                    }
                    else
                    {
                        Log.d("Profileerrorresponse", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<List<ProileModel>>, t: Throwable)
                {
                    Log.d("FailureResponse", t.message.toString())
                }

            })

        return profile_response
    }

    fun completeLogin(userName:String,password:String,userType:String):MutableLiveData<LoginModel>
    {
        var login_response = MutableLiveData<LoginModel>()
        var map = HashMap<String,String>()
        map.put("userName",userName)
        map.put("password",password)
        map.put("userTypeId",userType)
        ServiceApi().doLogin(map)
            .enqueue(object : Callback<LoginModel>
            {
                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>)
                {
                    if (response.isSuccessful)
                    {
                        login_response.value = response.body()
                    }
                    else
                    {
                        Log.d("Errorresponse", response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<LoginModel>, t: Throwable)
                {
                    Log.d("ResponseFailure", t.message.toString())
                }

            })

        return login_response
    }

    fun checkUser(userName:String,userType:String):MutableLiveData<DuplicateUserModel>
    {
        var checkUserResponse = MutableLiveData<DuplicateUserModel>()
        ServiceApi().checkforUser(userName,userType)
            .enqueue(object :Callback<DuplicateUserModel>
            {
                override fun onResponse(
                    call: Call<DuplicateUserModel>,
                    response: Response<DuplicateUserModel>
                ) {
                    if (response.isSuccessful)
                    {
                        checkUserResponse.value = response.body()
                    }
                    else
                    {
                        Log.d(TAG, "onErrorResponse: "+response.code())

                    }
                }

                override fun onFailure(call: Call<DuplicateUserModel>, t: Throwable)
                {
                    Log.d(TAG, "onFailure: "+t.toString())
                }

            })

        return checkUserResponse
    }

    fun sendOtpFromMobile(mobile_no:String):MutableLiveData<SendOtpModel>
    {
        var sendotp_response = MutableLiveData<SendOtpModel>()
        ServiceApi().sendOtp(mobile_no)
            .enqueue(object : Callback<SendOtpModel>
            {
                override fun onResponse(
                    call: Call<SendOtpModel>,
                    response: Response<SendOtpModel>
                ) {
                   if (response.isSuccessful)
                   {
                       sendotp_response.value = response.body()
                   }
                    else
                   {
                       Log.d(TAG, "onErrorResponse: "+response.message().toString())
                   }
                }

                override fun onFailure(call: Call<SendOtpModel>, t: Throwable)
                {
                    Log.d(TAG, "onFailure: "+t.message.toString())
                }

            })

        return sendotp_response
    }

    fun onVerifyOtp(mobile:String,otp:String):MutableLiveData<SendOtpModel>
    {
        var otp_verify_response = MutableLiveData<SendOtpModel>()
        ServiceApi().verifyOtp(mobile,otp)
            .enqueue(object : Callback<SendOtpModel>
            {
                override fun onResponse(
                    call: Call<SendOtpModel>,
                    response: Response<SendOtpModel>
                ) {
                    if (response.isSuccessful)
                    {
                        otp_verify_response.value = response.body()
                    }
                    else
                    {
                        Log.d(TAG, "onVerifyOtpError: "+response.message().toString())
                    }
                }

                override fun onFailure(call: Call<SendOtpModel>, t: Throwable)
                {
                    Log.d(TAG, "onVerifyOtpFailure: "+t.message)
                }

            })
        return otp_verify_response
    }

    fun signup(userName: String,userType: String,password: String):MutableLiveData<SignUpModel>
    {
      var signUpresponse = MutableLiveData<SignUpModel>()
      ServiceApi().createAccount(userName,userType,password)
          .enqueue(object : Callback<SignUpModel>
          {

              override fun onResponse(call: Call<SignUpModel>, response: Response<SignUpModel>)
              {
                  if (response.isSuccessful)
                  {
                      signUpresponse.value = response.body()
                  }
                  else
                  {
                      Log.d(TAG, "onSignupError: "+response.errorBody().toString())
                  }
              }

              override fun onFailure(call: Call<SignUpModel>, t: Throwable)
              {
                  Log.d(TAG, "onSignupFailure: "+t.message.toString())
              }

          })
        return signUpresponse
    }

    public fun getBottomMenu():MutableLiveData<BottomMenuModel>
    {
        var menu_response = MutableLiveData<BottomMenuModel>()
        ServiceApi().bottomMenu()
            .enqueue(object : Callback<BottomMenuModel>
            {
                override fun onResponse(
                    call: Call<BottomMenuModel>,
                    response: Response<BottomMenuModel>
                ) {
                    if (response.isSuccessful)
                    {
                        menu_response.value = response.body()
                    }
                    else
                    {
                        Log.d(TAG, "onMenuResponse: "+response.errorBody())
                    }
                }

                override fun onFailure(call: Call<BottomMenuModel>, t: Throwable)
                {
                    Log.d(TAG, "onMenuFailure: "+t.message)
                }

            })
        return menu_response
    }
}