package com.auro.projectinclusion.Activity

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.auro.projectinclusion.Adapter.MenuPagerAdapter
import com.auro.projectinclusion.Model.BottomMenuModel
import com.auro.projectinclusion.Model.MenuModel
import com.auro.projectinclusion.R
import com.auro.projectinclusion.auth.AuthViewModel
import com.auro.projectinclusion.auth.BottommenuListener
import com.auro.projectinclusion.databinding.ActivityDashBoardBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DashBoardActivity : AppCompatActivity(),BottommenuListener
{
    private lateinit var mViewPager:ViewPager2
    private lateinit var mTablayout:TabLayout
    private lateinit var mAdapter:MenuPagerAdapter
    private var bottom_menuList = ArrayList<MenuModel>()
    private var mUrl = "https://esleave.com/lms_ziiei_prj/?id=82&pi_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5NjUwMzg5NDM2IiwianRpIjoiYTU0NWIyMjgtM2NmMC00NWIyLTljMDctYjVmMmRiZjBkMWQ3IiwiZXhwIjoxNjUzNzQ1MDM5LCJpc3MiOiJUZXN0LmNvbSIsImF1ZCI6IlRlc3QuY29tIn0.5xCJDCkg21Y-IbI_jcIzkcAKxDwH5KR04qwsMP5kzfY"
    private var mMap = HashMap<String,String>()
    private lateinit var mBinding:ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_dash_board)
        val viewmodel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.viewmodel = viewmodel
        viewmodel.mBottommenuListener = this
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.background_color)
        mViewPager = findViewById(R.id.viewpager)
        mTablayout = findViewById(R.id.tablayout)


        var menuModel = MenuModel(R.drawable.ic_icon_home,"Home")
        var menuModel1 = MenuModel(R.drawable.ic_article_icon,"Courses")
        var menuModel2 = MenuModel(R.drawable.msg_icon,"Certificate")
        var menuModel3 = MenuModel(R.drawable.profile_icon,"Refer")
        var menuModel4 = MenuModel(R.drawable.ic_settings_icon,"More")
       /* var menuModel5 = MenuModel(R.drawable.order_icon,"Orders")
        var menuModel6 = MenuModel(R.drawable.icon_book,"Books")*/

        bottom_menuList.add(menuModel)
        bottom_menuList.add(menuModel1)
        bottom_menuList.add(menuModel2)
        bottom_menuList.add(menuModel3)
        bottom_menuList.add(menuModel4)
       /* bottom_menuList.add(menuModel5)
        bottom_menuList.add(menuModel6)*/
        mAdapter = MenuPagerAdapter(this,bottom_menuList)
        mViewPager.adapter = mAdapter
        TabLayoutMediator(mTablayout,mViewPager,object :TabLayoutMediator.TabConfigurationStrategy
        {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int)
            {
                tab.setText(bottom_menuList.get(position).title)
                tab.setIcon(bottom_menuList.get(position).icon)
            }

        }).attach()
        mViewPager.setCurrentItem(0)

        mTablayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab?)
            {
                tab!!.getIcon()!!.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }

            override fun onTabUnselected(tab: TabLayout.Tab?)
            {
                tab!!.getIcon()!!.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            }

            override fun onTabReselected(tab: TabLayout.Tab?)
            {

            }

        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    class MyBrowser : WebViewClient()
    {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean
        {
            view!!.loadUrl(url!!)
            return true
        }
    }

    override fun onMenuCallStart() {

    }

    override fun onMenuCallSuccess(menu_response: MutableLiveData<BottomMenuModel>) {

    }

    override fun onMenuCallFail() {

    }
}