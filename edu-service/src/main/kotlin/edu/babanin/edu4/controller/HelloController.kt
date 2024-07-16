package edu.babanin.edu4.controller

import edu.babanin.edu4.model.HelloDepthResponse
import edu.babanin.edu4.model.HelloResponse
import edu.babanin.edu4.service.HelloService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/hello")
class HelloController(val helloService: HelloService) {
    @GetMapping
    suspend fun hello(): HelloResponse {
        return HelloResponse(
            value = "Hello World",
            info = "Your best service!"
        )
    }

    @GetMapping("/depth")
    suspend fun depthHello(): HelloDepthResponse {
        return HelloDepthResponse(helloService.getDepthHello())
    }
}