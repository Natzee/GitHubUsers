package com.example.githubusers.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.githubusers.data.GitHubUserRepository
import com.example.githubusers.data.Result
import com.example.githubusers.presenter.model.GitHubUser
import com.example.githubusers.presenter.model.ListItem
import com.example.githubusers.presenter.model.UIState
import com.example.githubusers.util.toGitHubUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(val gitHubUserRepository: GitHubUserRepository) :
    ViewModel() {

    private val _getUsers: MutableLiveData<UIState<List<ListItem<out GitHubUser>>>> = MutableLiveData()
    val getUsers: LiveData<UIState<List<ListItem<out GitHubUser>>>> = _getUsers

    val getUserList = ArrayList<GitHubUser>()

    var lastSyncedUserId = 0



    fun getUsers() {
        viewModelScope.launch {
            when (val gitHubUser = gitHubUserRepository.getUserList(lastSyncedUserId, 200)) {
                is Result.Success -> {
                    val users = gitHubUser.data.map { it.toGitHubUser() }
                    getUserList.addAll(users)
                    val listeItem : MutableList<ListItem.ContentItem<GitHubUser>> = getUserList.map { ListItem.ContentItem(it.id,it) }.toMutableList()
                    val loaderItem = ListItem.FooterItem(-1)
                    lastSyncedUserId = users.last().id
                    _getUsers.value = UIState.UIItems(listeItem+loaderItem)
                }
                is Result.Error -> {
                    _getUsers.value = UIState.UIError("")
                }
            }

        }
    }

    fun searchUser(id:Int){

    }


}