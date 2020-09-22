package com.project.dev_ops.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

@Component
class CipherConfig(
        @Value(value = "security.code")
        private val key: String) {

    private val algorithm = "PBKDF2WithHmacSHA512"

    fun encode(password: String?): String {
        val keySpec = PBEKeySpec(password!!.toCharArray(), key.toByteArray(StandardCharsets.UTF_8), 65536, 256)
        val secretKeyFactory = SecretKeyFactory.getInstance(algorithm)
        val securePassword = secretKeyFactory.generateSecret(keySpec).encoded
        return Base64.getEncoder().encodeToString(securePassword)
    }
}