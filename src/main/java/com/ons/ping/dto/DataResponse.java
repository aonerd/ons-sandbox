package com.ons.ping.dto;

import java.util.Map;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Introspected
@Serdeable
public class DataResponse {
    private Map<String, User> users;
    private Map<String, RolePermission[]> role_permissions;

    @Data
    @Builder
    @Serdeable
    public static class User {
        private String name;
        private String[] roles;
        private Location location;
    }

    @Data
    @Builder
    @Serdeable
    public static class Location {
        private String country;
        private String ip;
    }

    @Data
    @Builder
    @Serdeable
    public static class RolePermission {
        private String action;
        private String type;
    }
}
