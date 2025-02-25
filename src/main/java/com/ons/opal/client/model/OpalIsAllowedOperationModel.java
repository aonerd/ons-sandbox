package com.ons.opal.client.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OpalIsAllowedOperationModel {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Serdeable
    public static class Request {
        private Input input;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Serdeable
    public static class Input {
        private String user;
        private String action;
        private String object;
        private String type;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Serdeable
    public static class Response {
        private boolean result;
    }
}
