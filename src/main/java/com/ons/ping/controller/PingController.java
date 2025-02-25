package com.ons.ping.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/v1/ping")
public class PingController {

    @Get()
    public String ping() {
        log.info("Ping request received");
        return "pong v2";
    }

}
