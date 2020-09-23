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

    fun base64Encode(password: String?): String {
        return Base64.getEncoder().encodeToString(secure(password))
    }

    //Useless functionality, use just in test
    fun base64Decode(password: String?): String {
        return String(Base64.getDecoder().decode(secure(password)))
    }

    fun secure(password: String?): ByteArray {
        val keySpec = PBEKeySpec(password!!.toCharArray(), key.toByteArray(StandardCharsets.UTF_8), 65536, 256)
        val secretKeyFactory = SecretKeyFactory.getInstance(algorithm)
        return secretKeyFactory.generateSecret(keySpec).encoded
    }


}