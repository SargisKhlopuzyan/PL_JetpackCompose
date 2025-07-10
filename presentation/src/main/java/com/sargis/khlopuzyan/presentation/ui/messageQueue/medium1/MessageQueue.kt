package com.sargis.khlopuzyan.presentation.ui.messageQueue.medium1

class MessageQueue {

    var head: Message? = null
    var tail: Message? = null

    fun run() {
        Thread(object : Runnable {
            override fun run() {
                while (true) {
                    if (head != null) {

                        var current = head

                        while (current != null && current.execTime > System.currentTimeMillis()) {
                            current = current.next
                        }

                        if (current != null) {
                            val prev = current.prev
                            val next = current.next

                            current.task?.run()

                            current.prev = null
                            current.next = null

                            next?.prev = prev
                            prev?.next = next

                            if (prev == null) {
                                head = next
                            }
                        }
                    }
                }
            }
        }).start()
    }

    fun post(task: Runnable) {
        val message = Message(task, System.currentTimeMillis())
        if (head == null) {
            head = message
        } else if (tail == null) {
            tail = message
            head?.next = tail
            tail?.prev = head
        } else {
            message.prev = tail
            tail?.next = message
            tail = message
        }
    }

    fun post(task: Runnable, millis: Long) {
        val message = Message(task, System.currentTimeMillis() + millis)
        if (head == null) {
            head = message
        } else if (tail == null) {
            tail = message
            head?.next = tail
            tail?.prev = head
        } else {
            message.prev = tail
            tail?.next = message
            tail = message
        }
    }
}