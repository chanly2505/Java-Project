package com.phoneshope.java.project.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.phoneshope.java.project.config.security.PermissionEnum.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {

    ADMIN(Set.of(BRAND_WRITE,BRAND_READ,MODEL_WRITE,MODEL_READ)) ,
    SALE(Set.of(BRAND_READ,MODEL_READ));

    private Set<PermissionEnum> permissionEnumSet;

    public  Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> grantedAuthorities = this.permissionEnumSet.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
                .collect(Collectors.toSet());
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+ this.name());
        grantedAuthorities.add(role);
        return grantedAuthorities;
    }

}
