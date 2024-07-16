package edu.babanin.edu4.service

import edu.babanin.edu4.client.HelloClient
import org.springframework.stereotype.Service

@Service
class HelloService(
    private val helloClient: HelloClient
) {

    suspend fun getDepthHello(): String {
        val (hello, info) = helloClient.getHello()

        return "$hello - $info"
    }
}