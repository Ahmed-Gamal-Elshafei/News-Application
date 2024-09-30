package com.example.newsapplication.ui.navigation

import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : NavigationItem("home", "Home", Icons.Default.Home)
    object Favourites : NavigationItem("favourites", "Favourites", Icons.Default.Favorite)
    object ArticleContent : NavigationItem(
        "article_content_screen",
        "ArticleContent",
        Icons.Default.Search
    ) {
        fun createRoute(articleUrl: String) =
            "article_content_screen/${Uri.encode(articleUrl)}"
    }
}