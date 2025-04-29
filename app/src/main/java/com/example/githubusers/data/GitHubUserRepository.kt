package com.example.githubusers.data

import kotlinx.coroutines.flow.SharedFlow

interface GitHubUserRepository {

    suspend fun getUserList(lastSyncedUserId:Int,perPage:Int) : Result<List<GitHubUserEntity>>
}