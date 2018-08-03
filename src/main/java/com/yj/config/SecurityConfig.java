package com.yj.config;

import com.yj.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService detailsService;

    @Autowired
    CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf()
//                .disable()
//                .httpBasic().disable()
//                .logout().disable()
//                .exceptionHandling()
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/logout").permitAll()
//                .antMatchers("/wechat/login").permitAll()
//                .anyRequest().authenticated();
//        httpSecurity.headers().frameOptions().disable();



        httpSecurity
                .csrf()
                .disable()
                .httpBasic().disable()
                .logout().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers("/**").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/wechat/login/**").permitAll()
                .anyRequest().authenticated();
//        httpSecurity.exceptionHandling().authenticationEntryPoint(new Http401AuthenticationEntryPoint("Bearer realm=\"webrealm\""));
//        httpSecurity.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());
        httpSecurity.headers().frameOptions().disable();

        httpSecurity.addFilterBefore(corsFilter, ChannelProcessingFilter.class);

    }

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(detailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService);
        auth.authenticationProvider(authenticationProvider());
    }
}