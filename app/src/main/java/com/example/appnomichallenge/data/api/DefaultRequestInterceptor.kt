package com.example.appnomichallenge.data.api

import com.example.appnomichallenge.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class DefaultRequestInterceptor : Interceptor {

    companion object {
        private const val CONTENT_TYPE = "application/json"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()

        original.newBuilder().apply {
            addHeader("Content-Type", CONTENT_TYPE)
            addHeader("Api-Key", BuildConfig.API_KEY)
            addHeader("Alias-Key",BuildConfig.ALIAS_KEY)
            url(original.url)
            return chain.proceed(build())
        }
    }
}