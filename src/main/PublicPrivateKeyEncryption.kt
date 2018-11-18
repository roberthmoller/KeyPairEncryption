package main

import main.util.*
import java.security.*
import javax.crypto.Cipher



open class PublicPrivateKeyEncryption(val bits: Int = 2048,
                                      val encryptionMethod: String = defaultEncryptionMethod){
    private val transform: String = "$encryptionMethod/ECB/OAEPWithSHA1AndMGF1Padding"

    /**
     * Gets instance of initialised cipher
     */
    fun cipher(mode: Int, key: Key): Cipher =
        Cipher.getInstance(transform).apply { this.init(mode, key) }

    /**
     * Generates a public & private key in a pair
     */
    fun gernerateKeyPair(): KeyPair = KeyPairGenerator.getInstance(encryptionMethod)
        .apply { this.initialize(bits) }
            .genKeyPair()

    /**
     * Encrypts a string with a key string
     */
    fun encrypt(message: String, key: PublicKey): String =
        cipher(Cipher.ENCRYPT_MODE, key)
            .doFinal(message.toByteArray()).toBase64()

    /**
     * Alias for encrypt
     */
    fun encrypt(message: String, key: String): String = encrypt(message, key.toPublicKey())


    /**
     * Decrypts a string with a key string
     */
    fun decrypt(message: String, key: PrivateKey): String = String(
        cipher(Cipher.DECRYPT_MODE, key)
            .doFinal(message.fromBase64()))

    /**
     * Alias for decrypt
     */
    fun decrypt(message: String, key: String): String = decrypt(message, key.toPrivateKey())
}