package com.jmanrique.marvelproject.app.di

import com.jmanrique.marvelproject.BuildConfig
import com.jmanrique.marvelproject.data.network.APIConstants
import com.jmanrique.marvelproject.data.network.MarvelAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val DATE_FORMAT = "dd/MM/yyyy"
        private const val CONNECT_TIMEOUT = 20L
        private val TAG = NetworkModule::class.java.simpleName
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun providesOkHttpClient(bodyInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(bodyInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("ts", APIConstants.ts)
                    .addQueryParameter("apikey", APIConstants.API_KEY)
                    .addQueryParameter("hash", APIConstants.hash())
                    .addQueryParameter("limit", APIConstants.limit)
                    .build()
                request.url(url)
                val response = chain.proceed(request.build())
                return@addInterceptor response
            }
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun providesApi(retrofit: Retrofit): MarvelAPI = retrofit.create(MarvelAPI::class.java)


}