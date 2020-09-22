package com.project.dev_ops.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@Order(1)
class SecurityConfig : WebSecurityConfigurerAdapter(true) {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .exceptionHandling().and()
                .csrf().disable()
                .anonymous().and()
                .servletApi().and()
                .headers().cacheControl()
                .and().and().authorizeRequests()
                .antMatchers(*AUTH_WHITELIST).permitAll()
                .antMatchers("/api/authorisation/sign-up", "/", "/eureka/**").permitAll()
                .anyRequest().authenticated()
        http.headers().cacheControl().disable()
    }

    companion object {
        private val AUTH_WHITELIST = arrayOf( // -- swagger ui
                "/swagger-resources/**",
                "/swagger-ui.html/**",
                "/swagger-ui.html",
                "/v2/api-docs/**"
        )
    }
}