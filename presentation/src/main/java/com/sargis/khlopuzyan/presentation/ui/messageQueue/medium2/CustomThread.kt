package com.sargis.khlopuzyan.presentation.ui.messageQueue.medium2

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.MessageQueue
import android.util.Log
import java.util.concurrent.ConcurrentLinkedQueue

class CustomThread : Thread("CustomThread") {

    private var count = 0

    override fun run() {
//       val messageQueue = MessageQueue()
       val messageQueue = ConcurrentLinkedQueue<Runnable>()
        messageQueue.add {

        }

        Log.e("LOG_TAG", "run()")
        Looper.prepare()
        val myLooper = Looper.myLooper()!!
        handler = object : Handler(myLooper) {
            override fun handleMessage(msg: Message) {
                count += msg.what
                Log.e(
                    "LOG_TAG",
                    "handleMessage -> currentThread().name: ${currentThread().name} | looper.thread.name: ${looper.thread.name} | msg: ${msg.what} | count: $count"
                )
                looper.thread.interrupt()
                if (count > 20) {
                    val mainHandler = Handler(Looper.getMainLooper())
                    mainHandler.post {
                        Log.e(
                            "LOG_TAG",
                            "handleMessage -> currentThread().name: ${currentThread().name}"
                        )
                    }
                    myLooper.quit()
                }
            }
        }
        Looper.loop()
    }

    companion object {
        lateinit var handler: Handler
    }
}