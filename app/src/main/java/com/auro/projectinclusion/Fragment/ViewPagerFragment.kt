package com.auro.projectinclusion.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.TextView
import com.auro.projectinclusion.Activity.DashBoardActivity
import com.auro.projectinclusion.R


class ViewPagerFragment : Fragment() {

    private lateinit var mWebview: WebView
    private var mUrl = "https://esleave.com/lms_ziiei_prj/?id=82&pi_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI5NjUwMzg5NDM2IiwianRpIjoiYTU0NWIyMjgtM2NmMC00NWIyLTljMDctYjVmMmRiZjBkMWQ3IiwiZXhwIjoxNjUzNzQ1MDM5LCJpc3MiOiJUZXN0LmNvbSIsImF1ZCI6IlRlc3QuY29tIn0.5xCJDCkg21Y-IbI_jcIzkcAKxDwH5KR04qwsMP5kzfY"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_view_pager, container, false)
        mWebview = view.findViewById(R.id.webView)
        var text:TextView = view.findViewById(R.id.frag_text)
        var bundle = arguments
        text.setText(bundle!!.getString("key"))
        if (text.text.contentEquals("Home"))
        {
           text.visibility = View.GONE

            mWebview.setWebViewClient(DashBoardActivity.MyBrowser())
            mWebview.setWebChromeClient(WebChromeClient())
            mWebview.getSettings().setLoadsImagesAutomatically(true)
            mWebview.getSettings().setJavaScriptEnabled(true)
            mWebview.getSettings().setAllowFileAccess(true)
            mWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
            mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true)
            mWebview.getSettings().setPluginState(WebSettings.PluginState.ON)
            mWebview.getSettings().setMediaPlaybackRequiresUserGesture(false)
            mWebview.loadUrl(mUrl)
        }
        else
        {
            text.visibility = View.VISIBLE
            mWebview.visibility = View.GONE
        }
        return view
    }


}