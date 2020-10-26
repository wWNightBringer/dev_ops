package com.project.dev_ops.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
@Configuration
@Order(1)
class SecurityConfig(private val userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter(true) {

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
                .antMatchers("/api/authorisation/**", "/", "/eureka/**", "/shop/**", "/store/**").permitAll()
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

    @Autowired
    @Throws(java.lang.Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(userDetailsService).passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}