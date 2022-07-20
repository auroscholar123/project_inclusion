package com.auro.projectinclusion.Broadcast_Reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import android.widget.EditText

class OTP_Reciever() : BroadcastReceiver()
{

    override fun onReceive(p0: Context?, p1: Intent?)
    {
        var messages = Telephony.Sms.Intents.getMessagesFromIntent(p1)
        for (sms in messages)
        {
            var msg = sms.messageBody
            Log.d("Broadcast", "onReceive: "+msg)
        }
    }

}