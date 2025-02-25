package com.ons.user.controller;

import com.ons.opal.client.OpalClientOperations;
import com.ons.opal.client.model.OpalIsAllowedOperationModel;
import com.ons.user.dto.Account;
import com.ons.user.dto.DataResponse;
import com.ons.user.dto.DataResponse.User;
import com.ons.user.util.DataResponseUtil;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/v1/users")
@ExecuteOn(TaskExecutors.BLOCKING)
public class UserController {

    private static DataResponse dataResponse = DataResponseUtil.createDataResponse();

    @Inject
    private OpalClientOperations opalClientOperations;

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


    @Get("/{username}/accounts/{accountId}")
    public HttpResponse<Account> getAccount(@PathVariable String username, @PathVariable String accountId) {
        log.info("Account request received for user: {} and account: {}", username, accountId);

        final HttpResponse<OpalIsAllowedOperationModel.Response> allowed =
                opalClientOperations.isAllowed(OpalIsAllowedOperationModel.Request.builder()
                .input(OpalIsAllowedOperationModel.Input.builder()
                        .action("read")
                        .user(username)
                        .object(accountId)
                        .type("account")
                        .build())
                .build());

        log.info("Is allowed response: {}", allowed.body());

        if (allowed.body().isResult()){

            return HttpResponse.ok(dataResponse.getAccounts().getOrDefault(accountId, null));
        } else{
            return HttpResponse.unauthorized();
        }
    }

//    @Post("/{username}/accounts")
//    public Account addAccount(@PathVariable String username, @Body Account account) {
//        log.info("Add account request received for user: {}", username);
//        dataResponse.getAccounts().put(username, account);
//        return dataResponse.getAccounts().getOrDefault(username, null);
//    }
}
