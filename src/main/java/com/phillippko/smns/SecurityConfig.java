package com.phillippko.smns;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //.authorizeRequests()
                //.antMatchers("/input").hasRole("ADMIN")
                //.antMatchers("get").hasRole("USER")
                //.antMatchers("/login").permitAll()
                //.anyRequest().permitAll();
                .authorizeRequests()
//                .antMatchers("/measurement").hasAnyRole("ROLE_ADMIN", "ROLE_SENSOR")
//                .antMatchers("/measurements").authenticated()
                .anyRequest().permitAll();
//                .and()
//                .logout().and().formLogin();
    }

    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            auth
                    .inMemoryAuthentication()
                    .withUser("admin").password(encoder.encode("1234")).roles("ADMIN").and()
                    .withUser("user").password(encoder.encode("1234")).roles("USER").and()
                    .withUser("sensor").password(encoder.encode("1234")).roles("SENSOR");
        }
    }
}
