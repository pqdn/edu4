package edu.babanin.edu4.client

import edu.babanin.edu4.model.HelloResponse
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class HelloClient(
    private val helloWebClient: WebClient,
) {
    suspend fun getHello(): HelloResponse {
        return helloWebClient
            .get()
            .uri("/v1/hello")
            .retrieve()
            .bodyToMono(HelloResponse::class.java)
            .awaitSingle()
    }

}