package com.example.githubusers.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppToolBar(modifier: Modifier,toolBarName:String) {

    Box(modifier.fillMaxWidth()) {
        Text(toolBarName)
    }
}

@Preview
@Composable
fun AppToolBarPreview() {
    AppToolBar(Modifier,"GitHub Users")
}