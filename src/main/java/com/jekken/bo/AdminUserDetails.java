package com.jekken.bo;

import com.jekken.pojo.SellAdmin;
import com.jekken.pojo.SellResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity用户详情
 * Create by Jekken
 * 2020/4/4 18:50
 */
public class AdminUserDetails implements UserDetails {
    private SellAdmin sellAdmin;
    private List<SellResource> resourceList;

    public AdminUserDetails(SellAdmin sellAdmin, List<SellResource> resourceList) {
        this.sellAdmin = sellAdmin;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return resourceList.stream().map(role->new SimpleGrantedAuthority(role.getId()+":"+role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return sellAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return sellAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sellAdmin.getStatus().equals(1);
    }
}
