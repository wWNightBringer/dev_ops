package com.project.dev_ops.config

import com.project.dev_ops.filter.WebSecurityFilter
import com.project.dev_ops.service.TokenUserService
import com.project.dev_ops.service.impl.UserDetailsServiceImpl
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
@Order(1)
class SecurityConfig(
        private val userDetailsServiceImpl: UserDetailsServiceImpl,
        private val tokenUserService: TokenUserService) : WebSecurityConfigurerAdapter(true) {

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
                .antMatchers("/api/authorisation/**", "/", "/eureka/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(WebSecurityFilter(tokenUserService, userDetailsServiceImpl), UsernamePasswordAuthenticationFilter::class.java)

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
        auth.userDetailsService<UserDetailsService>(userDetailsServiceImpl).passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}