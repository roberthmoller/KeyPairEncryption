package main

import main.util.meassureElapsedTime

class RSA(bitSize: Int): PublicPrivateKeyEncryption(bitSize, "RSA")

fun main(args: Array<String>) {
    testSpeed(listOf(
            RSA(512), RSA(1024), // 1024 seems overall fastest
            RSA(2048), RSA(4096)
        ))
}

fun testSpeed(subjects: List<PublicPrivateKeyEncryption>) {

    for (subject in subjects) {
        println("${subject.bits}b: ${meassureElapsedTime { subject.gernerateKeyPair() }}ms")
    }

    println("[Encryption]")
    val p = "Lorem ipsum dolor amet"
    for (subject in subjects) {
        val kp = subject.gernerateKeyPair()
        println("${subject.bits}b: ${meassureElapsedTime { subject.encrypt(p, kp.public) }}ms")
    }

    println("[Decryption]")
    for (subject in subjects) {
        val kp = subject.gernerateKeyPair()
        val e = subject.encrypt(p, kp.public)
        println("${subject.bits}b: ${meassureElapsedTime { subject.decrypt(e, kp.private) }}ms")
    }
}