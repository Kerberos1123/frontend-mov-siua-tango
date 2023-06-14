package com.una.FrontEndTango.Service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.una.FrontEndTango.BuildConfig
import com.una.FrontEndTango.Utils.AuthorizationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * We create a builder of the retrofit object which can be reused for all method calls
 * declared in the RestApi interface.
 */
object ServiceBuilder {
    var gson: Gson = GsonBuilder()
        .setDateFormat(BuildConfig.DATE_FORMAT)
        .create()

    // If you need to check your request change the Level
    var loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.NONE
    )

    // Increased the time out to the server
    private val client =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(AuthorizationInterceptor()).build()

    private val retrofit = Retrofit.Builder()
        //.baseUrl(BuildConfig.BASE_URL) // change this IP for testing by your actual machine IP
        //.baseUrl("https://648170e829fa1c5c5031637a.mockapi.io/") // change this IP for testing by your actual machine IP
        //.baseUrl("http://localhost:8080")
        .baseUrl("https://backend-mov-siua-tango-v01.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}