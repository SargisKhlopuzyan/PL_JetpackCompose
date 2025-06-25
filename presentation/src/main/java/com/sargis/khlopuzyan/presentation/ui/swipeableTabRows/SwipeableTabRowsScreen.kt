package com.sargis.khlopuzyan.presentation.ui.swipeableTabRows

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sargis.khlopuzyan.presentation.ui.shoppingList.common.CommonTopAppBar

@Composable
fun SwipeableTabRowsScreen(navController: NavController) {

    val tabItems = listOf<TabItem>(
        TabItem(
            title = "Home",
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home
        ),
        TabItem(
            title = "Browse",
            unselectedIcon = Icons.Outlined.ShoppingCart,
            selectedIcon = Icons.Filled.ShoppingCart
        ),

        TabItem(
            title = "Account",
            unselectedIcon = Icons.Outlined.AccountCircle,
            selectedIcon = Icons.Filled.AccountCircle
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CommonTopAppBar("Swipeable Tab Rows") {
                navController.popBackStack()
            }
        }
    ) { contentPadding ->

        var selectedTabIndex by remember {
            mutableIntStateOf(0)
        }

        var pagerState = rememberPagerState {
            tabItems.size
        }

        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }

//        LaunchedEffect(pagerState.currentPage) {
//            selectedTabIndex = pagerState.currentPage
//        }

        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress) {
                selectedTabIndex = pagerState.currentPage
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {


//            ScrollableTabRow(
            TabRow(
                selectedTabIndex = selectedTabIndex
            ) {
                tabItems.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedTabIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = null
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = tabItems[index].title)
                }
            }
        }
    }
}

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
)

@Preview
@Composable
fun SwipeableTabRowsScreenPreview() {
    val navController = rememberNavController()
    SwipeableTabRowsScreen(navController)
}