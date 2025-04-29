package com.example.githubusers.di

import com.example.githubusers.data.GitHubAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class GitHubUserApiServiceModule {
    @Provides
    fun provideGitHubUserApiService(): GitHubAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubAPI::class.java)
    }
}