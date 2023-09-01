package com.phoneshope.java.project.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/","index.html","css/**","js/**")
                .permitAll()

               // .antMatchers("/models").hasRole(RoleEnum.SALE.name())
                //.antMatchers(HttpMethod.POST,"/brand").hasAuthority(BRAND_WRITE.getDescription())
                //.antMatchers(HttpMethod.GET,"/brand").hasAuthority(BRAND_READ.getDescription())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        UserDetails  userDetails1= User.builder()
                .username("chanLy")
                .password(passwordEncoder.encode("chanly123"))
                .authorities(RoleEnum.SALE.getGrantedAuthorities())
                .build();
        UserDetails   userDetails2= User.builder()
                .username("chan")
                .password(passwordEncoder. encode("chan"))
                .authorities(RoleEnum.ADMIN.getGrantedAuthorities())
                .build();
        UserDetailsService detailsService = new InMemoryUserDetailsManager(userDetails1, userDetails2);
        return detailsService;
    }
}