package com.yj.security;

import com.yj.domain.user.model.User;
import com.yj.domain.user.service.OrganizationService;
import com.yj.domain.user.service.RoleService;
import com.yj.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class MyUserDetailsService implements UserDetailsService {
    public static class MyUserDetail extends org.springframework.security.core.userdetails.User {

        private Long userId;
        public MyUserDetail(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
            this.userId = userId;
        }

        public MyUserDetail(Long userId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            this.userId = userId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }
    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (username.isEmpty()) {
            throw new UsernameNotFoundException("用户名为空");
        }

        User user = new User();
        user.setId(123L);
        user.setUsername("zwh");
        user.setPassword("$2a$10$IpuThEHKhDSfTxgXFlCwV.V.VobyQ/5Neips6azRzbYDQiqgORqjK");
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("test"));
//        for(Role role : roleService.getRoles(wechatUser.getId())){
//            if (role != null) { authorities.add(new SimpleGrantedAuthority(role.getRoleName())); }
//        }




        return new MyUserDetail(
                user.getId(),
                username, user.getPassword(),
                true,//是否可用
                true,//是否过期
                true,//证书不过期为true
                true,//账户未锁定为true
                authorities);
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}