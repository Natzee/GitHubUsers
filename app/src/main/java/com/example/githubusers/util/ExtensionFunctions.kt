package com.example.githubusers.util

import com.example.githubusers.data.GitHubUserEntity
import com.example.githubusers.presenter.model.GitHubUser

fun GitHubUserEntity.toGitHubUser() : GitHubUser = GitHubUser(name = name,id = id)