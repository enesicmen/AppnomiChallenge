package com.example.appnomichallenge.di.module

import com.example.appnomichallenge.BuildConfig
import com.example.appnomichallenge.data.Constants
import com.example.appnomichallenge.data.api.DefaultRequestInterceptor
import com.example.appnomichallenge.di.qualifier.DefaultInterceptor
import com.example.appnomichallenge.di.qualifier.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class HTTPClientModule {

    @Provides
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient = builder.build()

    @Provides
    fun provideOkHttpBuilder(
        @DefaultInterceptor requestInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: Interceptor,
        timeout: Int
    ): OkHttpClient.Builder{
        val builder =
            OkHttpClient.Builder()
                .connectTimeout(timeout.toLong(),TimeUnit.SECONDS)
                .readTimeout(timeout.toLong(),TimeUnit.SECONDS)
                .addInterceptor(requestInterceptor)
                .retryOnConnectionFailure(true)
                .followRedirects(true)
        if(BuildConfig.DEBUG){
            builder.addInterceptor(loggingInterceptor)
        }
        return builder
    }

    @Provides
    @DefaultInterceptor
    fun provideDefaultRequestInterceptor(): Interceptor = DefaultRequestInterceptor()

    @Provides
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideTimeOut() = Constants.Api.TIMEOUT
}