package com.jmanrique.marvelproject.data.network

import com.jmanrique.marvelproject.app.di.NetworkModule
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class APIConstants {

    companion object {
        val ts = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "f699b3a0bac92c3e5de7514188126be3"
        private const val PRIVATE_KEY = "b871da0f1f81e0b1511d7bc275c02cf5c1693820"
        const val limit = "100"

        fun hash(): String {
            val input = "$ts$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}