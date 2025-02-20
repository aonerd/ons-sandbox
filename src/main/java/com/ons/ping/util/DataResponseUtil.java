package com.ons.ping.util;

import com.ons.ping.dto.DataResponse;
import java.util.HashMap;
import java.util.Map;

public class DataResponseUtil {

    public static DataResponse createDataResponse() {
        Map<String, DataResponse.User> users = new HashMap<>();
        Map<String, DataResponse.RolePermission[]> rolePermissions = new HashMap<>();

        users.put("alice", DataResponse.User.builder()
                .roles(new String[]{"admin"})
                .location(DataResponse.Location.builder()
                        .country("US")
                        .ip("8.8.8.8")
                        .build())
                .build());

        users.put("aiden", DataResponse.User.builder()
                .roles(new String[]{"employee", "billing"})
                .location(DataResponse.Location.builder()
                        .country("US")
                        .ip("8.8.8.8")
                        .build())
                .build());

        users.put("sunil", DataResponse.User.builder()
                .roles(new String[]{"guest"})
                .location(DataResponse.Location.builder()
                        .country("US")
                        .ip("8.8.8.8")
                        .build())
                .build());

        users.put("eve", DataResponse.User.builder()
                .roles(new String[]{"customer"})
                .location(DataResponse.Location.builder()
                        .country("US")
                        .ip("8.8.8.8")
                        .build())
                .build());

        rolePermissions.put("customer", new DataResponse.RolePermission[]{
                DataResponse.RolePermission.builder().action("read").type("dog").build(),
                DataResponse.RolePermission.builder().action("read").type("cat").build(),
                DataResponse.RolePermission.builder().action("adopt").type("dog").build(),
                DataResponse.RolePermission.builder().action("adopt").type("cat").build()
        });

        rolePermissions.put("employee", new DataResponse.RolePermission[]{
                DataResponse.RolePermission.builder().action("read").type("dog").build(),
                DataResponse.RolePermission.builder().action("read").type("cat").build(),
                DataResponse.RolePermission.builder().action("update").type("dog").build(),
                DataResponse.RolePermission.builder().action("update").type("cat").build()
        });

        rolePermissions.put("billing", new DataResponse.RolePermission[]{
                DataResponse.RolePermission.builder().action("read").type("finance").build(),
                DataResponse.RolePermission.builder().action("update").type("finance").build()
        });

        rolePermissions.put("guest", new DataResponse.RolePermission[]{
                DataResponse.RolePermission.builder().action("read").type("cat").build(),
                DataResponse.RolePermission.builder().action("read").type("finance").build()
        });

        return DataResponse.builder()
                .users(users)
                .role_permissions(rolePermissions)
                .build();
    }
}
