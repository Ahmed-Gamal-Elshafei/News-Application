package com.example.newsapplication.ui.screen


import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ShowArticleContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    articleUrl: String = "https://www.google.com"
) {
    // show article content
    //val context = LocalContext.current
    Box(modifier = modifier.fillMaxSize()) {
        // show article content into webView
        AndroidView(factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
            }
        }, update = { webView ->
            webView.loadUrl(articleUrl)
        })

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(64.dp)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Share, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "")
            }
        }
    }
}


