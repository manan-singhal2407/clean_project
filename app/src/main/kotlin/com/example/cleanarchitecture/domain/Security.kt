package com.example.cleanarchitecture.domain

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

const val ALGORITHM_AES = "AES"
private const val KEY_SIZE = 256

class Security {

    fun generatePassphrase(): ByteArray {
        val keyGenerator = KeyGenerator.getInstance(ALGORITHM_AES)
        keyGenerator.init(KEY_SIZE)
        return keyGenerator.generateKey().encoded
    }

    fun encrypt(text: String, key: SecretKey?) = crypto(inString = text, key = key, decrypt = false)
    fun decrypt(text: String, key: SecretKey?) = crypto(inString = text, key = key, decrypt = true)

    private fun crypto(inString: String, key: SecretKey?, decrypt: Boolean): String {
        val cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM)
        val inputByte = inString.toByteArray()
        return if (decrypt) {
            cipher.init(Cipher.DECRYPT_MODE, key)
            String(cipher.doFinal(Base64.decode(inputByte, Base64.DEFAULT)))
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key)
            String(Base64.encode(cipher.doFinal(inputByte), Base64.DEFAULT))
        }
    }

    companion object {
        const val ENCRYPTION_ALGORITHM = "AES/ECB/PKCS5Padding"
    }
}