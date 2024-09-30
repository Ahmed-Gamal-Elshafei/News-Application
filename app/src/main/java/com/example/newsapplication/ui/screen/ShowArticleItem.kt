package com.example.newsapplication.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapplication.data.model.Article
import com.example.newsapplication.ui.navigation.NavigationItem

@Composable
fun ShowArticleItem(
    article: Article,
    isFavorite: MutableState<Boolean>,
    navController: NavController,
    insertArticleIntoDb: () -> Unit = {}
) {

    val title = article.title
    val description = article.description.toString()
    val imageUrl = article.urlToImage


    Card(shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                // Handle item click
                // navigate to article screen
                val route = NavigationItem.ArticleContent.createRoute(article.url)
                Log.d("ShowArticleItemRoute", "ShowArticleItem: $route")
                navController.navigate(route)

            }) {
        Row(
            modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.9f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description, fontSize = 14.sp, color = Color.Gray, maxLines = 2
                )
            }
            IconButton(modifier = Modifier.size(24.dp),

                onClick = {
                    // Handle bookmark click
                    // add article to db
                    Log.d("InsertArticle", "ShowArticleItem: $title")
                    insertArticleIntoDb()
                }) {
                Icon(
                    imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Bookmark"
                )
            }
        }
    }
}

//@Preview
//@Composable
//private fun ShowArticlesListPreview() {
//    ShowArticleItem("Fake Title",
//        "Israel killed a senior Hezbollah commander and other leaders in a strike on southern Beirut. It comes after deadly pager and walkie-talkie attacks this week.",
//        "https://media.cnn.com/api/v1/images/stellar/prod/gettyimages-2172148567.jpg?c=16x9&q=w_800,c_fill",
//        ,
//        remember { mutableStateOf(false) })
//}