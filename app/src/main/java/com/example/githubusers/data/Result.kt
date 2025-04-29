package com.example.githubusers.data

sealed class Result<out T>  {

    data class Success<out T>(val data: T) : Result<T>()
    sealed class Error : Result<Nothing>() {
        data object NetworkError : Error()
    }

}