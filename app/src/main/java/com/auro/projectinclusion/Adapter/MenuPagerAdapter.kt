package com.auro.projectinclusion.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.auro.projectinclusion.Fragment.CertificateFragment
import com.auro.projectinclusion.Fragment.CoursesFragment
import com.auro.projectinclusion.Fragment.ReferFragment
import com.auro.projectinclusion.Fragment.ViewPagerFragment
import com.auro.projectinclusion.Model.MenuModel

class MenuPagerAdapter(var fragmentActivity: FragmentActivity, var dataList:ArrayList<MenuModel>) : FragmentStateAdapter(fragmentActivity)
{

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun createFragment(position: Int): Fragment
    {
        var fragment = Fragment()
        if (position==0)
        {
             fragment = ViewPagerFragment()
            var bundle = Bundle()
            bundle.putString("key",dataList.get(position).title)
            fragment.arguments = bundle
        }

        if (position==1)
        {
            fragment = CoursesFragment()
            var bundle = Bundle()
            bundle.putString("key",dataList.get(position).title)
            fragment.arguments = bundle
        }

        if (position==2)
        {
            fragment = CertificateFragment()
            var bundle = Bundle()
            bundle.putString("key",dataList.get(position).title)
            fragment.arguments = bundle
        }


        if (position==3)
        {
            fragment = ReferFragment()
            var bundle = Bundle()
            bundle.putString("key",dataList.get(position).title)
            fragment.arguments = bundle
        }

        return fragment
    }
}