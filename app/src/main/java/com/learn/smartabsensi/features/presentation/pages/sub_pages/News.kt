package com.learn.smartabsensi.features.presentation.pages.sub_pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.features.data.models.ArticlesItem
import com.learn.smartabsensi.features.presentation.components.news.TopBarPreviousPage

@Composable
fun News(
    modifier: Modifier = Modifier,
    news: ArticlesItem,
    onPreviousPage: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBarPreviousPage(
                onPreviousPage = onPreviousPage
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Background)
                .padding(paddingValues)
                .padding(horizontal = 18.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = news.title ?: "",
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(15.dp))
            AsyncImage(
                model = news.urlToImage,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth().height(250.dp)
            )
            Spacer(Modifier.height(15.dp))
            Text(
                text = news.description ?: "",
                color = TextPrimary,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}