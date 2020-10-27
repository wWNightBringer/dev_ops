package com.project.dev_ops.service.impl

import com.project.dev_ops.model.enum.RolesValue
import com.project.dev_ops.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList

@Component
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findUserByUsername(username)
                .map { v -> createSecurityUser(v!!, username) }
                .orElseThrow { throw UsernameNotFoundException(username) }
    }

    private fun createSecurityUser(user: com.project.dev_ops.model.entity.User, username: String?): User {
        if (username == null) throw UsernameNotFoundException(username)

        val grantedAuthorities = ArrayList<GrantedAuthority>()
        if (user.roleId == 1)
            grantedAuthorities.addAll(getRolesByUser(RolesValue.ANONYMOUS))
        if (user.roleId == 3)
            grantedAuthorities.addAll(getRolesByUser(RolesValue.USER))
        if (user.roleId == 2)
            grantedAuthorities.addAll(getRolesByUser(RolesValue.ADMIN))

        return User(username, user.password, grantedAuthorities)

    }

    private fun getRolesByUser(rolesValue: RolesValue): MutableList<GrantedAuthority> {
        return when (rolesValue) {
            RolesValue.ANONYMOUS ->
                Collections.singletonList(SimpleGrantedAuthority(RolesValue.ANONYMOUS.value)) as MutableList<GrantedAuthority>
            RolesValue.USER ->
                Collections.singletonList(SimpleGrantedAuthority(RolesValue.USER.value)) as MutableList<GrantedAuthority>
            else -> {
                val list = ArrayList<GrantedAuthority>()
                for (value in RolesValue.values()) {
                    list.add(SimpleGrantedAuthority(value.value))
                }
                list
            }
        }
    }
}