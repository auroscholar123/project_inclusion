package com.auro.projectinclusion.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.auro.projectinclusion.R
import com.auro.projectinclusion.getData
import com.auro.projectinclusion.isNetwork
import com.auro.projectinclusion.toast

class SplashActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.background_color)
        var token_value = getData(this,"user_token")
        var user_id = getData(this,"user_id")
        if (token_value!="null")
        {
            Handler().postDelayed({

                if (isNetwork(this))
                {
                    val intent = Intent(this, DashBoardActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    toast("Please Check your Internet Connection")

                }

            }, 3000) // 3000 is the delayed time in milliseconds.
        }
        else if (user_id!=="null")
        {
            Handler().postDelayed({

                if (isNetwork(this))
                {
                    val intent = Intent(this, DashBoardActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    toast("Please Check your Internet Connection")


                }

            }, 3000) // 3000 is the delayed time in milliseconds.
        }
        else
        {
            Handler().postDelayed({

                if (isNetwork(this))
                {
                    val intent = Intent(this, LanguageActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    toast("Please Check your Internet Connection")


                }

            }, 3000)
        }
    }
        }




