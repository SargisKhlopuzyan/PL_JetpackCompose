package com.sargis.khlopuzyan.presentation.ui.messageQueue.medium1

class Message(
    val task: Runnable?,
    val execTime: Long = -1,
) {
    var prev: Message? = null
    var next: Message? = null
}