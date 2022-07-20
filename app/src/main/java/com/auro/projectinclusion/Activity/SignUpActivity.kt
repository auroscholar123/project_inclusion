package com.auro.projectinclusion.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.auro.projectinclusion.Model.DuplicateUserModel
import com.auro.projectinclusion.Model.SignUpModel
import com.auro.projectinclusion.R
import com.auro.projectinclusion.auth.AuthViewModel
import com.auro.projectinclusion.auth.CheckForUser
import com.auro.projectinclusion.auth.SignUpAuthListener
import com.auro.projectinclusion.databinding.ActivitySignUpBinding
import com.auro.projectinclusion.saveData
import com.auro.projectinclusion.toast

class SignUpActivity : AppCompatActivity(),SignUpAuthListener,CheckForUser
{
    private var TAG = "SignuPActivity"
    private lateinit var mBinding:ActivitySignUpBinding
    private lateinit var mUsertype:String
    private var mUser = false
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.background_color)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.viewmodel = viewModel
        viewModel!!.mSignUpAuthListener = this
        viewModel!!.mCheckUserListener = this
        mUsertype = intent.getStringExtra("user_type").toString()
        mBinding.signupBack.setOnClickListener {
            finish()
        }
        mBinding.showPassSignupimg.setOnClickListener {
            mBinding.editSignupPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            mBinding.showPassSignupimg.visibility = View.GONE
            mBinding.hidePassSignupimg.visibility = View.VISIBLE
        }
        mBinding.hidePassSignupimg.setOnClickListener {
            mBinding.editSignupPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            mBinding.hidePassSignupimg.visibility = View.GONE
            mBinding.showPassSignupimg.visibility = View.VISIBLE
        }
        mBinding.signupContinueButton.setOnClickListener {

            if (viewModel.name_signup.isNullOrEmpty())
            {
                mBinding.editName.setError("Enter Name")
            }
            else if (viewModel.username_signup.isNullOrEmpty()||!mUser)
            {
                mBinding.editSignupPassword.setError("Enter Username")
            }
            else if (viewModel.password_signup.isNullOrEmpty())
            {
                mBinding.editSignupPassword.setError("Enter Password")
            }
            else
            {
                mBinding.signupProgress.visibility = View.VISIBLE
               viewModel.signUpUser(viewModel.username_signup!!,mUsertype,viewModel.password_signup!!)
            }
        }
        mBinding.editUsername.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
            {
                viewModel.CheckforExistingUser(mBinding.editUsername.text.toString(),mUsertype)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    override fun onSignUpNetwork() {
        Log.d(TAG, "onSignUpNetwork: ")
    }

    override fun onSignUpSuccess(signup_response: MutableLiveData<SignUpModel>)
    {
        Log.d(TAG, "onSignUpSuccess: ")
        signup_response.observe(this, Observer {
            mBinding.signupProgress.visibility = View.GONE
            var status = it.status.toString()
            var msg = it.message.toString()
            if (status.equals("1"))
            {
                var user_id = it.response?.id.toString()
                Log.d(TAG, "onSignUpSuccess: "+user_id)
                saveData(this,"user_id",user_id)
                toast("User Registered Successfully")
                startActivity(Intent(this,DashBoardActivity::class.java))
            }
            else
            {
                toast("User Already Registered")
            }
        })
    }

    override fun onSignUpFailure() {
        Log.d(TAG, "onSignUpFailure: ")
    }

    override fun onNetworkcall() {
        Log.d(TAG, "onNetworkcall: ")
    }

    override fun onUserSuccess(existingUserResponse: MutableLiveData<DuplicateUserModel>) {
        existingUserResponse.observe(this, Observer {
            var isDuplicateUser = it.isDuplicateUser.toString()
            var message = it.message.toString()
            if (isDuplicateUser.equals("0"))
            {
               mBinding.editUsername.setError("Username Available")
           //     mBinding.editUsername.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.check,0)
                mUser = true

            }
            else
            {
         //      mBinding.editUsername.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0)
               mBinding.editUsername.setError(message)
                mUser = true
            }
        })
    }

    override fun onuserFalse() {
        Log.d(TAG, "onuserFalse: ")
    }


}