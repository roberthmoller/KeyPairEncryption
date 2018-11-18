package main.util

import java.security.Key
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*

const val defaultEncryptionMethod = "PublicPrivateKeyEncryption"
/**
 * Creates base64 string from key
 */
fun <KeyType : Key> KeyType.key() = this.encoded.toBase64()

/**
 * Creates public key from string
 */
fun String.toPublicKey(method: String = defaultEncryptionMethod): PublicKey = KeyFactory
    .getInstance(method)
        .generatePublic(
            X509EncodedKeySpec(
                this.fromBase64()))

/**
 * Creates public key from string
 */
fun String.toPrivateKey(method: String = defaultEncryptionMethod): PrivateKey = KeyFactory
    .getInstance(method)
        .generatePrivate(
            PKCS8EncodedKeySpec(
                this.fromBase64()))

/**
 * Creates base64 string from a byte array
 */
fun ByteArray.toBase64(): String = Base64.getEncoder().encodeToString(this)!!

/**
 * Creates a byte array from a base64 string
 */
fun String.fromBase64(): ByteArray = Base64.getDecoder().decode(this)!!

/**
 * Creates a base64 string from a string
 */
fun String.toBase64(): String = this.toByteArray().toBase64()

/**
 * Creates a string from a byte array
 */
fun ByteArray.toString() = String(this)
