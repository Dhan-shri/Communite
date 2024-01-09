package com.dhanshri.community

import android.app.Application
import com.google.firebase.FirebaseApp


// Launching point of the application
class LoginFlowApp: Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

    }
}