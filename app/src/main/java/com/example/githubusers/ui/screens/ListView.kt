package com.example.githubusers.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubusers.presenter.model.GitHubUser
import com.example.githubusers.presenter.model.ListItem

@Composable
fun <T> ListView(modifier: Modifier, userList: List<ListItem<out T>>, onClickUser: (Int) -> Unit, onLoadMore: ()->Unit) {


    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index ?: -1
        }.collect { lastVisibleIndex ->
            if (lastVisibleIndex >= userList.size - 1) {
                onLoadMore()
            }
        }
    }

    LazyColumn(modifier = modifier.fillMaxWidth(), state = listState) {
        items(userList, key = { it.id }) { user ->
            when(user){
                is ListItem.ContentItem ->{
                    val gitUser = user.data as GitHubUser
                    Column(modifier = Modifier.clickable { onClickUser(user.id) }) {
                        Text(text = gitUser.name)
                    }
                }

                is ListItem.FooterItem->{
                    Box(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                        CircularProgressIndicator(modifier=Modifier.align(Alignment.Center))
                    }
                }
            }

        }

    }
}

@Preview
@Composable
fun ListViewPreview() {
    ListView<String>(Modifier, listOf(), {},{})
}