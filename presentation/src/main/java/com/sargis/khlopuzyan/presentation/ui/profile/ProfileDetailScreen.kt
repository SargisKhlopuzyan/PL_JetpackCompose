package com.sargis.khlopuzyan.presentation.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presentation.ui.shoppingList.common.CommonTopAppBar

@Composable
fun ProfileDetailScreen(navController: NavController, name: String?) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeContent,
        topBar = {
            CommonTopAppBar("Profile Screen") {
                navController.popBackStack()
            }
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .padding(horizontal = 50.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "Hello, $name"
            )
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    ProfileDetailScreen(rememberNavController(), "Test")
}