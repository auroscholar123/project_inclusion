package com.auro.projectinclusion.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.auro.projectinclusion.*
import com.auro.projectinclusion.Adapter.ChooseProfileAdapter
import com.auro.projectinclusion.Model.ProileModel
import com.auro.projectinclusion.Url.AllApi
import com.auro.projectinclusion.auth.AuthViewModel
import com.auro.projectinclusion.auth.ProfileAuthListener
import com.auro.projectinclusion.databinding.ActivityChooseProfileBinding

class ChooseProfileActivity : AppCompatActivity(),ProfileAuthListener,ClickProfileId
{
    private var TAG = "ChooseProfileActivity"
    private lateinit var mBinding:ActivityChooseProfileBinding
    private lateinit var mProfileList:ArrayList<ProileModel>
    private  var mProfileId = ""
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.background_color)

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_choose_profile)
        var lang_id:String = getData(this,"lang_id")
        if (lang_id.equals("2"))
        {
            setAppLocale(this,"hi")
            mBinding.userBttn.setText(R.string.cont_text)
            mBinding.selectText.setText(R.string.please_select_txt)
        }
        else
        {
            setAppLocale(this,"en")
            mBinding.userBttn.setText(R.string.cont_text)
            mBinding.selectText.setText(R.string.please_select_txt)
        }
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.viewmodel = viewModel
        viewModel.mProAuthListener = this
        mBinding.usertypeProgress.visibility = View.VISIBLE
        viewModel.getProfileData()
        mBinding.chooseProfileBttn.setOnClickListener {
            if (mProfileId.isNullOrEmpty())
            {
                toast("Please Select Profile")
            }
            else
            {

                startActivity(Intent(this,DashBoardActivity::class.java)
                    .putExtra("userTypeId",mProfileId))
            }

        }


    }

    override fun onProfileNetworkStarted()
    {

    }

    override fun onProfileApiSuccess(profile_response: MutableLiveData<List<ProileModel>>)
    {
        mProfileList = ArrayList()
        mProfileList.clear()
       profile_response.observe(this, Observer {
           for (i in 0 until it.size)
           {
               mBinding.usertypeProgress.visibility = View.GONE
               var name = it.get(i).name.toString()
               var profile_id = it.get(i).id.toString()
               var profile_icon = ""
               if (!it.get(i).icon.equals(null))
               {
                   profile_icon = it.get(i).icon.toString()
               }
               else
               {

               }

               var complete_icon_url = AllApi.IMAGE_URL+profile_icon
               mProfileList.add(ProileModel(name,profile_id,complete_icon_url,""))
               mBinding.profileRecycler.layoutManager = LinearLayoutManager(this)
               mBinding.profileRecycler.adapter = ChooseProfileAdapter(this,mProfileList,this)
               Log.d("profileData", name)
           }
       })
    }

    override fun onProfileApiFailure() {

    }

    override fun clickToGetProfileId(profileId: String) {
        mProfileId = profileId
        Log.d(TAG, "clickToGetProfileId: ")
    }
}