package com.example.githubusers.presenter.model

sealed class UIState<out T> {
    data class UIItems<out T>(val items:T) : UIState<T>()
    data object UILoad : UIState<Nothing>()
    data class UIError(val errorMessage:String) : UIState<Nothing>() // if i put nothing it work but unit doesnot wwork

}