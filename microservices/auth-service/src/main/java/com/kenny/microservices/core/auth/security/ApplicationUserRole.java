package com.kenny.microservices.core.auth.security;

import com.google.common.collect.Sets;
import static com.kenny.microservices.core.auth.security.ApplicationUserPermission.*;
import java.util.Set;

public enum ApplicationUserRole {
    CUSTOMER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ACCOUNT_READ, ACCOUNT_WRITE, CUSTOMER_READ, CUSTOMER_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
        this.permissions = permissions;
    }
    public Set<ApplicationUserPermission> getPermissions(){
        return permissions;
    }
}
