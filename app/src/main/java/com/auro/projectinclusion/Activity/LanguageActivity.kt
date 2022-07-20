package com.auro.projectinclusion.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.auro.projectinclusion.*
import com.auro.projectinclusion.Adapter.LanguageAdapter
import com.auro.projectinclusion.Interface.GetLanguageID
import com.auro.projectinclusion.Model.LanguageModel
import com.auro.projectinclusion.auth.MyAuthListener
import com.auro.projectinclusion.auth.AuthViewModel
import com.auro.projectinclusion.databinding.ActivityLanguageBinding
import java.util.*
import kotlin.collections.ArrayList

class LanguageActivity : AppCompatActivity(),MyAuthListener,GetLanguageID
{
    private lateinit var mLangList:ArrayList<LanguageModel>
    private lateinit var mBinding:ActivityLanguageBinding
    private lateinit var mViewModel: AuthViewModel
    private  var mLangId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.background_color)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_language)
        mViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.viewmodel = mViewModel
        mViewModel.mAuthListener = this


        mBinding.languageProgress.visibility = View.VISIBLE

        mBinding.langButton.setOnClickListener {
            if (mLangId!=null)
            {
                saveData(this@LanguageActivity,"lang_id",mLangId.toString())
                startActivity(Intent(this,ChooseProfileActivity::class.java))

            }
            else
            {
              toast("Please Select Language")
            }

        }



    }

    override fun onResume() {
        super.onResume()
        mLangId = null
        mViewModel.getLanguageData()
    }

    override fun onNetworkCallStarted()
    {
        Log.d("networkcall", "onNetworkStarted: ")
    }

    override fun onApiSuccess(lang_response: MutableLiveData<List<LanguageModel>>)
    {

        mLangList = ArrayList()
        mLangList.clear()
        lang_response.observe(this, Observer {
            Log.d("Listsize", it.size.toString())
           for (i in 0 until it.size)
           {
               var name = it.get(i).name.toString()
               var lang_id = it.get(i).id
               Log.d("namelang", name.toString())
               mLangList.add(LanguageModel(name,lang_id,"",""))
           }
            mBinding.languageProgress.visibility = View.GONE
            mBinding.languageRecycler.layoutManager = GridLayoutManager(this,2)
            mBinding.languageRecycler.adapter = LanguageAdapter(this,mLangList,this)
        })
    }

    override fun onApiFailure()
    {
        Log.d("networkcall", "onApiFailure: ")
    }

    override fun getLangId(lang_id: Int?)
    {
        mLangId = lang_id!!
        Log.d("LangId", mLangId.toString())
        if (mLangId!!.equals(2))
        {
            setAppLocale(this,"hi")
          mBinding.langSelectionText.setText(R.string.language_choose)
          mBinding.langPrefText.setText(R.string.lang_pref_string)
          mBinding.langBttnTxt.setText(R.string.cont_text)
          mBinding.welcomeText.setText(R.string.welcome_txt)
        }
        else
        {
            setAppLocale(this,"en")
            mBinding.langSelectionText.setText(R.string.language_choose)
            mBinding.langPrefText.setText(R.string.lang_pref_string)
            mBinding.langBttnTxt.setText(R.string.cont_text)
            mBinding.welcomeText.setText(R.string.welcome_txt)
        }
    }


}