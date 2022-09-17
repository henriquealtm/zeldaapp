package com.example.commons.extension

fun String.capitalize() = this.replaceFirstChar { it.uppercase() }