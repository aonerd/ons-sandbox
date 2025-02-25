package com.ons.opal.client;

import com.ons.opal.client.model.OpalIsAllowedOperationModel.Request;
import com.ons.opal.client.model.OpalIsAllowedOperationModel.Response;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;

public interface OpalClientOperations {
    @Post(
            uri = "/v1/data/app/rbac/allow"
    )
    default HttpResponse<Response> isAllowed(@Body Request request) {
        return HttpResponse.ok(Response.builder().result(false).build());
    }
}
