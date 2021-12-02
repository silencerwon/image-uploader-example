package com.silencer.imageapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {
    @GetMapping("/hello")
    fun hello(name: String) = "Hello $name!!"
}