package com.example.receiveandsendingsms

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.Manifest


import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var REQUEST_CODE_SEND_SMS = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Code for Android 4.4 (KitKat) and above
            Log.d("VersionCheck", "Running on Android version: ${Build.VERSION.RELEASE}")
        } else {
            // Code for versions below Android 4.4 (KitKat)
            Log.d(
                "VersionCheck",
                "Running on an older version of Android: ${Build.VERSION.RELEASE}"
            )
        }

//Request Runtime Permissions: Add code to request runtime permissions if your app targets API level 23 (Android 6.0) or higher:

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_SMS
                    ), 1
                )
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.SEND_SMS),
                    REQUEST_CODE_SEND_SMS
                )
            }

        }

    }
}

/*
Static Registration: If you want Myreceiver to always listen for incoming SMS, declare it in your AndroidManifest.xml file
Dynamic Registration: If you only need Myreceiver to be active while MainActivity is running, you can register it in onCreate() or onStart() and unregister it in onStop() or onDestroy(): and by using dynamic registration you can show the message to textview.



 */
