package com.sargis.khlopuzyan.presentation.ui.messageQueue.medium2

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

class CustomHandler(looper: Looper) : Handler(looper) {
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        Log.e("LOG_TAG", "handleMessage -> looper.thread.name: ${looper.thread.name} | msg: ${msg.what}")
    }
}