package com.sargis.khlopuzyan.presentation.ui.messageQueue.medium1

/**
 * @param requestUrl - http request url
 * @param callback - the callback for request has been done
 * */
data class Request(val requestUrl: String, val callback: Runnable)