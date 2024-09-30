package com.example.newsapplication

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapplication.ui.navigation.NavigationItem
import com.example.newsapplication.ui.screen.ShowArticleContent
import com.example.newsapplication.ui.screen.ShowArticlesList
import com.example.newsapplication.ui.screen.ShowSavedArticlesList
import com.example.newsapplication.ui.theme.HotNewsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HotNewsTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet {
            Text("News App", modifier = modifier.padding(16.dp))
            Spacer(modifier = modifier.height(2.dp))
            NavigationDrawerItem(label = { Text(text = "Home") }, selected = false, onClick = {
                navController.navigate(NavigationItem.Home.route)
            })
            NavigationDrawerItem(label = { Text(text = "Saved") },
                selected = false,
                onClick = { navController.navigate(NavigationItem.Favourites.route) })
        }
    }, content = {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = "News App")
            }, navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                }
            })
        }) { paddingValue ->

            SetUpNavigation(
                modifier = Modifier.padding(paddingValue), navController = navController
            )
        }

    }

    )
}

@Composable
fun SetUpNavigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItem.Home.route
    ) {
        composable(NavigationItem.Home.route) {
            ShowArticlesList(navController = navController)
        }
        composable(NavigationItem.Favourites.route) {
            ShowSavedArticlesList(navController = navController)
        }
        composable(
            route = "article_content_screen/{articleUrl}",
            arguments = listOf(navArgument("articleUrl") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val articleUrl =
                backStackEntry.arguments?.getString("articleUrl")?.let { Uri.decode(it) }
                    ?: "https://www.google.com"
            ShowArticleContent(navController = navController, articleUrl = articleUrl)
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MainScreen()
}


