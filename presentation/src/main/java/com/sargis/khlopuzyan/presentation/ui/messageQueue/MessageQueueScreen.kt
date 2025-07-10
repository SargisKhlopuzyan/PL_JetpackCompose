package com.sargis.khlopuzyan.presentation.ui.messageQueue

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sargis.khlopuzyan.presentation.ui.messageQueue.medium2.CustomThread
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MessageQueueScreen() {
    val customThread = CustomThread()
    customThread.start()

//    val customHandler = CustomHandler(customThread.looper)

    val scope = rememberCoroutineScope()
    var count = 0

    Button(
        modifier = Modifier.padding(start = 50.dp, top = 300.dp),
        onClick = {

            scope.launch {
                delay(300)
//                customThread.count = 0
                val handler = CustomThread.handler
                handler.sendEmptyMessage(count++)
                val looper = handler.looper
//                if (count == 2) {
//                    looper.quit()
//                }
            }

//            scope.launch {
//                delay(300)
//                CustomThread.handler.post {
//                    Log.e("LOG_TAG", "thread.handler.post")
//                }
//                CustomThread.handler.sendEmptyMessage(10)
//            }

//            scope.launch(Dispatchers.IO) {

            /*
            val messageQueue = MessageQueue()
            messageQueue.run()

            messageQueue.post(object : Runnable {
                override fun run() {
                    Log.e("LOG_TAG", "messageQueue * 0 : ${Thread.currentThread().name}")
                }
            })

            messageQueue.post(object : Runnable {
                override fun run() {
                    Log.e("LOG_TAG", "messageQueue * 1")
                }
            }, 2000)

            messageQueue.post(object : Runnable {
                override fun run() {
                    Log.e("LOG_TAG", "messageQueue * 2")
                }
            })

            messageQueue.post(object : Runnable {
                override fun run() {
                    Log.e("LOG_TAG", "messageQueue * 3")
                }
            }, 1000)

            messageQueue.post(object : Runnable {
                override fun run() {
                    Log.e("LOG_TAG", "messageQueue * 4")
                }
            })

            messageQueue.post(object : Runnable {
                override fun run() {
                    Log.e("LOG_TAG", "messageQueue * 5")
                }
            }, 1500)
            */

            /*
            count++
            val request = Request(
                requestUrl = "Count: $count",
                callback = {
                    println("LOG_TAG -> Request callback - $count | ${Thread.currentThread().name}")
                }
            )
            volleyRequest.requestQueue.push(request)
//                request.callback.run()
            */
//            }
        }
    ) {
        Text(text = "Send event to MessageQueue")
    }
}