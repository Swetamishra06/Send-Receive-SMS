package com.example.receiveandsendingsms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.util.Log

//Myreceiver receives the service of  broadcast receiver
class Myreceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        //checks if OnReceive method triggers or not.
        Log.d("Details ", "onReceive triggered")
        //protocol data unit - pdu
        //in parameter intent is passed by Broadcast Server with lots of data ike - date ,time ,phone number  and most importantly  message of the sender with the help of putExtras method in intent.
        //bundle passing through intents hence create a variable of type bundle to accept
        var bundle: Bundle? = intent?.extras
        //this bundle variable of type bundle takes all data from intent by using getExtras() method.
        //pdus test be the key here to rececive the message
        //pdus plays a important role in OnReceive method()  to get the details of the sms .(pdus is pre defined key)
        // broadcast server send the data in form of object array hence to get it we need an aray so created here an array of type Any named smsArray.
        var smsArray = bundle?.get("pdus") as Array<Any>
//traverse through the array
        for (obj in smsArray) {
            val smsMessage =
                SmsMessage.createFromPdu(obj as ByteArray)// createFromPdu ()  method - gives the details of message that is - time , date ,number etc of the sender in form of byte array hence type casting required here.
            val originOfMessage =
                smsMessage.displayOriginatingAddress//  displayOriginatingAddress - show the origin of this message
            val message = smsMessage.displayMessageBody// display the message body
            Log.d("MsgDetails ", "Details  : $originOfMessage  and message is : $message")
  
            var smsmanager: SmsManager = SmsManager.getDefault()
            smsmanager.sendTextMessage(
                "+918076095088",
                null,
                "testing again android for sending message",
                null,
                null
            )
            // destination  number  - number with country code
            //source address - address of source
            //text - message
            //Receiver intent -  notification to get the message in receiving end.
            //sender intent  - notification/callback  of sending status has been shown to sender's phone . either message sent or message not sent.


        }


    }
}