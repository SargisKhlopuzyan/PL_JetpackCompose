package com.sargis.khlopuzyan.presentation.ui.messageQueue.medium1

import android.os.Handler
import android.os.Looper
import java.util.LinkedList

class VolleyRequest {
    //Message Queue
    val requestQueue = LinkedList<Request>()

    val thread = Thread(object : Runnable {
        override fun run() {
//            Looper.prepare()
            //start this new thread and use a while to do dead loop, keep
            //popping the head of the request queue and fire the callback.
            while (true) {
                if (requestQueue.isNotEmpty()) {
                    val request = requestQueue.pop() //poll()
                    Thread.sleep(1500)
                    // ignore the process of the request,
                    // will be consumed by HttClient api
                    // or HttpUrlConnection api.
                    val response = "Http request"
                    println("LOG_TAG -> request.requestUrl: ${request.requestUrl} | ${Thread.currentThread().name}")
                    //fire the callback in main thread
                    Handler(Looper.getMainLooper()).post {
                        request.callback.run()
                    }
                }
            }
        }
    }).start()
}