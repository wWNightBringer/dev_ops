package com.project.dev_ops.filter


import com.project.dev_ops.service.TokenUserService
import com.project.dev_ops.service.impl.UserDetailsServiceImpl
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class WebSecurityFilter(private val tokenUserService: TokenUserService,
                        private val userDetailsServiceImpl: UserDetailsServiceImpl) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val token = request.getHeader("Authorization")
        if (token != null && tokenUserService.validateToken(token)) {
            val username = tokenUserService.getUsernameFromToken(token)
            val loadUserByUsername = userDetailsServiceImpl.loadUserByUsername(username)
            val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(loadUserByUsername, null,
                    loadUserByUsername.authorities)
            SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
        }
        chain.doFilter(request, response)
    }
}