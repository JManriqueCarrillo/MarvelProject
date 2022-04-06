package com.jmanrique.marvelproject.utils.extensions

fun String?.safeValue(default: String = "") =
    this?.let {
        return@let it
    } ?: default
