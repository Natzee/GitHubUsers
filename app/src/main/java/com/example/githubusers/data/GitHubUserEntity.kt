package com.example.githubusers.data

import com.google.gson.annotations.SerializedName

data class GitHubUserEntity(
    @SerializedName("id")
    val id:Int,
    @SerializedName("login")
    val name:String)