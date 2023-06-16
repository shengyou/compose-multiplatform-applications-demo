package io.kraftsman.server.extensions

import java.security.MessageDigest
import java.util.Base64

fun String.md5(): String =
    MessageDigest.getInstance("MD5")
        .also {
            it.update(toByteArray())
        }.run {
            String(Base64.getEncoder().encode(digest()))
        }
