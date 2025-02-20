package com.ons.ping.controller;

import com.ons.ping.dto.DataResponse;
import com.ons.ping.dto.DataResponse.User;
import com.ons.ping.util.DataResponseUtil;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Body;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/v1/users")
public class UserController {

    private static DataResponse dataResponse = DataResponseUtil.createDataResponse();

    @Get()
    public DataResponse getUsers() {
        log.info("User data request received");
        return dataResponse;
    }

    @Post
    public DataResponse addUser(@Body User user) {
        log.info("Add user request received");
        dataResponse.getUsers().put(user.getName(), user);
        return dataResponse;
    }
}
