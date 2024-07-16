package edu.babanin.edu4.controller

import edu.babanin.edu4.model.HelloDepthResponse
import edu.babanin.edu4.model.HelloResponse
import edu.babanin.edu4.service.HelloService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {
    @LocalServerPort
    var port = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @MockBean
    lateinit var helloService: HelloService


    lateinit var baseUrl: String


    @BeforeEach
    fun setUp() {
        baseUrl = "http://localhost:$port"
    }

    @Test
    fun testHello() {

        val url = "$baseUrl/v1/hello"
        val response = restTemplate.getForObject(url, HelloResponse::class.java)

        assertEquals("Hello World", response.value)
        assertEquals("Your best service!", response.info)
    }

    @Test
    fun testDepthHello() = runBlocking {
        val expected = "depth hello"

        Mockito.`when`(helloService.getDepthHello())
            .thenReturn(expected)

        val url = "$baseUrl/v1/hello/depth"
        val response = restTemplate.getForObject(url, HelloDepthResponse::class.java)

        assertEquals(expected, response.value)
    }
}