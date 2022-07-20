package com.auro.projectinclusion.Url


import com.auro.projectinclusion.Model.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.logging.StreamHandler

interface ServiceApi
{
 @GET("Language")
 public fun getLanguage():Call<List<LanguageModel>>

 @GET("UserType")
 public fun getProfile():Call<List<ProileModel>>


 @POST("Login/UserLogin")
 public fun doLogin(@Body params:HashMap<String,String>):Call<LoginModel>

 @GET("Login/ValidateUser/{username}/{usertypeid}")
 public fun checkforUser(@Path("username")username:String,@Path("usertypeid")userType:String):Call<DuplicateUserModel>

 @POST("Login/SendOTP/{mobile_no}")
 public fun sendOtp(@Path("mobile_no")mobile_no:String):Call<SendOtpModel>

 @POST("Login/VerifyOTP/{mobile_no},{otp}")
 public fun verifyOtp(@Path("mobile_no")mobile_no: String,@Path("otp")otp:String):Call<SendOtpModel>

 @POST("Login/RegisterUser/{username},{usertype},{password}")
 public fun createAccount(@Path("username")username:String,@Path("usertype")user_type:String,@Path("password")password:String):Call<SignUpModel>

 @GET("bottom_menu")
 public fun bottomMenu():Call<BottomMenuModel>


 companion object
 {
  operator fun invoke():ServiceApi
  {
   return Retrofit.Builder()
        .baseUrl(AllApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServiceApi::class.java)
  }
 }
}