package com.example.githubusers.ui.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubusers.presenter.GitHubViewModel
import com.example.githubusers.presenter.model.UIState
import com.example.githubusers.ui.theme.GitHubUsersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val gitHubViewModel: GitHubViewModel by viewModels()
        setContent {
            GitHubUsersTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    AppToolBar(Modifier.padding(16.dp), "Git Hub Users")
                }) { innerPadding ->
                    LaunchedEffect(Unit) {
                        gitHubViewModel.getUsers()
                    }
                    val uiState by gitHubViewModel.getUsers.observeAsState(UIState.UILoad)
                    when (uiState) {
                        is UIState.UILoad -> {
                            Box(Modifier
                                .padding(innerPadding)
                                .fillMaxSize()) {
                                CircularProgressIndicator(Modifier.align(Alignment.Center))
                            }
                        }

                        is UIState.UIItems -> {
                            ListView(
                                Modifier.padding(innerPadding),
                                (uiState as UIState.UIItems).items,{
                                    gitHubViewModel.searchUser(it)
                                },{
                                    gitHubViewModel.getUsers()
                                }
                            )
                        }

                        is UIState.UIError -> {
                            Toast.makeText(
                                LocalContext.current,
                                (uiState as UIState.UIError).errorMessage ?: "", Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }
}

