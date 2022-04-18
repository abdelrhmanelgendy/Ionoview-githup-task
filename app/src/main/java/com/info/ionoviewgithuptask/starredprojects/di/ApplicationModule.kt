package com.info.ionoviewgithuptask.starredprojects.di

import com.info.ionoviewgithuptask.starredprojects.util.Constant
import com.info.ionoviewgithuptask.starredprojects.data.remote.webservice.GitHupApi
import com.info.ionoviewgithuptask.starredprojects.repository.StarredProjectsMainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideGitHupApi() = Retrofit.Builder()
        .baseUrl(Constant.GITHUP_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHupApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(gitHupApi: GitHupApi) =
        StarredProjectsMainRepository(gitHupApi)



}