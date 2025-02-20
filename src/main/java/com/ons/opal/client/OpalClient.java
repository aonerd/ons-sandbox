package com.ons.opal.client;

import com.ons.opal.client.model.OpalIsAllowedOperationModel;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Client("http://localhost:8181")
@ExecuteOn(TaskExecutors.BLOCKING)
public interface OpalClient extends OpalClientOperations {

    @Override
    HttpResponse<OpalIsAllowedOperationModel.Response> isAllowed(OpalIsAllowedOperationModel.Request request);
}
