package com.sargis.khlopuzyan.liveCoding

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.random.Random

class Request(val index: Int) {
    /***/
}

class Response(val index: Int, val waitingTime: Long) {
    /***/
}

interface LegacyApi {
    suspend fun doRequest(request: Request, onComplete: (Response) -> Unit)
}

class BatchLegacyHelper(
    private val legacyApi: LegacyApi,
) {
    suspend fun doRequests(
        requests: List<Request>,
        onComplete: (response: List<Response>) -> Unit,
    ) {
        val responses = arrayOfNulls<Response>(requests.size)
        var count = 0

        requests.forEachIndexed { index, request ->
            withContext(Dispatchers.IO) {
                legacyApi.doRequest(request = request, onComplete = { it ->
                    responses[index] = it
                    count++

                    if (count == requests.size) {
                        onComplete(responses.mapNotNull { it })
                    }
                })
            }
        }
    }
}

private fun main() = runBlocking {
    val legacyApi = object : LegacyApi {
        override suspend fun doRequest(
            request: Request,
            onComplete: (Response) -> Unit,
        ) {
            val random = Random.nextInt(40) * 100L
            delay(random)
            return onComplete(Response(request.index, random))
        }
    }

    val requests = listOf(
        Request(1),
        Request(2),
        Request(3),
        Request(4),
        Request(5),
    )
    BatchLegacyHelper(legacyApi).doRequests(requests) { responses ->
        responses.forEach { response ->
            println("LOG_TAG -> response: Response(index=${response.index}, waitingTime=${response.waitingTime})")
        }
    }
}