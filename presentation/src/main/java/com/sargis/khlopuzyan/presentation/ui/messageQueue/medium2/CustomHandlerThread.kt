package com.sargis.khlopuzyan.presentation.ui.messageQueue.medium2

import android.os.Looper

class CustomHandlerThread : Thread("CustomHandlerThread") {

    val looper: Looper
        get() = Looper.myLooper()!!

    override fun run() {
        Looper.prepare()
        Looper.loop()
    }

    companion object {
        lateinit var handler: CustomHandler
    }
}