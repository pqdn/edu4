package edu.babanin.edu4.service

import edu.babanin.edu4.client.HelloClient
import edu.babanin.edu4.model.HelloResponse
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class HelloServiceTest {

    @InjectMockKs
    lateinit var service: HelloService

    @MockK
    lateinit var helloClient: HelloClient

    @Test
    fun getDepthHello() = runBlocking {
        val value = "hello"
        val info = "info"
        val helloResponse = HelloResponse(value, info)
        coEvery { helloClient.getHello() } returns helloResponse

        val actual = service.getDepthHello()

        Assertions.assertEquals("$value - $info", actual)
    }
}