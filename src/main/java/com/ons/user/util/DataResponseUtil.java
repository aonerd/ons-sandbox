package com.ons.user.util;

import com.ons.user.dto.DataResponse;
import com.ons.user.dto.Account;
import java.util.HashMap;
import java.util.Map;

public class DataResponseUtil {

    public static DataResponse createDataResponse() {
        Map<String, DataResponse.User> users = new HashMap<>();
        Map<String, DataResponse.RolePermission[]> rolePermissions = new HashMap<>();
        Map<String, Account> accounts = new HashMap<>();

        users.put("alice", DataResponse.User.builder()
                .name("Alice")
                .roles(new String[]{"admin"})
                .accountIds(new String[]{"account1", "account2"})
                .location(DataResponse.Location.builder()
                        .country("US")
                        .ip("8.8.8.8")
                        .build())
                .build());

        users.put("aiden", DataResponse.User.builder()
                .name("Aiden")
                .roles(new String[]{"employee", "billing"})
                .accountIds(new String[]{"account3"})
                .location(DataResponse.Location.builder()
                        .country("US")
                        .ip("8.8.8.8")
                        .build())
                .build());

        users.put("sunil", DataResponse.User.builder()
                .name("Sunil")
                .roles(new String[]{"guest"})
                .accountIds(new String[]{"account2"})
                .location(DataResponse.Location.builder()
                        .country("US")
                        .ip("8.8.8.8")
                        .build())
                .build());

        users.put("eve", DataResponse.User.builder()
                .name("Eve")
                .roles(new String[]{"customer"})
                .accountIds(new String[]{"account5"})
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

        accounts.put("account1", Account.builder()
                .accountId("account1")
                .accountNumber("12345678")
                .sortCode("12-34-56")
                .build());

        accounts.put("account2", Account.builder()
                .accountId("account2")
                .accountNumber("87654321")
                .sortCode("65-43-21")
                .build());

        return DataResponse.builder()
                .users(users)
                .role_permissions(rolePermissions)
                .accounts(accounts)
                .build();
    }
}
