package com.example.githubusers.presenter.model

sealed class ListItem<T> {
    abstract val id: Int

    data class ContentItem<T>(override val id: Int, val data: T) : ListItem<T>()
    data class FooterItem(override val id: Int) : ListItem<Nothing>()
}