package com.example.githubusers.di

import com.example.githubusers.data.GitHubUserRepository
import com.example.githubusers.data.GitHubUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GitHubRepositoryModule {
    @Binds
    abstract fun bindGitHubRepositoryModule(gitHubUserRepositoryImpl: GitHubUserRepositoryImpl) : GitHubUserRepository
}