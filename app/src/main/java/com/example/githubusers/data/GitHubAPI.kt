package com.example.githubusers.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubAPI {
    @GET("users")
    suspend fun getUser(
        @Query("since")
        lastSyncedUserId:Int,
        @Query("per_page")
        perPage:Int) : Response<List<GitHubUserEntity>>

}