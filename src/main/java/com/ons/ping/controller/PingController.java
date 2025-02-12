package com.ons.ping.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/ping")
public class PingController {

    @Get
    public String ping() {
        return "pong";
    }
}
