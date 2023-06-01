package com.una.FrontEndTango.Utils


import com.una.FrontEndTango.Utils.MyApplication.Companion.sessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager?.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", it)
        }

        return chain.proceed(requestBuilder.build())
    }
}