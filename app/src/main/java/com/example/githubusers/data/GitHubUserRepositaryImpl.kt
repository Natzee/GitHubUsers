package com.example.githubusers.data

import javax.inject.Inject


class GitHubUserRepositoryImpl @Inject constructor(val gitHubAPI: GitHubAPI) : GitHubUserRepository {

    override suspend fun getUserList(
        lastSyncedUserId: Int,
        perPage: Int
    ): Result<List<GitHubUserEntity>> {

        val response = gitHubAPI.getUser(lastSyncedUserId, perPage)
        if (response.isSuccessful && response.body() != null) {
            return Result.Success(response.body()!!)
        } else {
            return Result.Error.NetworkError
        }

    }

}