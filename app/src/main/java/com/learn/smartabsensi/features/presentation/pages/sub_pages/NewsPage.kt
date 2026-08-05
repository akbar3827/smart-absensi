package com.learn.smartabsensi.features.presentation.pages.sub_pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.features.data.models.ArticlesItem
import com.learn.smartabsensi.features.presentation.components.news.NewsCard
import com.learn.smartabsensi.features.presentation.components.news.TopBarPreviousPage

@Composable
fun NewsPage(
    modifier: Modifier = Modifier,
    news: List<ArticlesItem?>,
    onPreviousPage: () -> Unit,
    onNews: (ArticlesItem) -> Unit
) {
    Scaffold(
        topBar = {
            TopBarPreviousPage(
                onPreviousPage = onPreviousPage
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(Background)
                .padding(horizontal = 18.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            news.filter { it != null }.forEach {
                item {
                    NewsCard(
                        modifier = Modifier.clickable {
                            if (it != null) {
                                onNews(it)
                            }
                        },
                        news = it
                    )
                }
            }
        }
    }
}