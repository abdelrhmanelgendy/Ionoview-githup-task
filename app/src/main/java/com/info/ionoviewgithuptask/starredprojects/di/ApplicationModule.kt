package com.info.ionoviewgithuptask.starredprojects.di

import androidx.viewbinding.BuildConfig
import com.info.ionoviewgithuptask.starredprojects.util.Constant
import com.info.ionoviewgithuptask.starredprojects.data.remote.webservice.GitHupApi
import com.info.ionoviewgithuptask.starredprojects.domain.repository.StarredProjectsMainRepository
import com.info.ionoviewgithuptask.starredprojects.util.helpers.NetworkStatusHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object
ApplicationModule {


    @Provides
    @BaseUrl
    @Singleton
    fun provideBaseUrl() = Constant.GITHUP_BASE_URL



    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideGitHupApi(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): GitHupApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(GitHupApi::class.java)



    @Provides
    @Singleton
    fun provideRepository(gitHupApi: GitHupApi,networkStatusHelper: NetworkStatusHelper) =
        StarredProjectsMainRepository(gitHupApi,networkStatusHelper)


}