package com.example.network

import okhttp3.OkHttpClient

internal fun getOkHttpClient() = OkHttpClient.Builder().build()