package com.learn.smartabsensi.features.presentation.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.smartabsensi.core.networkings.ApiConfig
import com.learn.smartabsensi.features.data.models.ArticleResponse
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private var _news = mutableStateOf<ArticleResponse?>(null)
    var news = _news

    fun fetchNews() {
        val q = "apple"
        val from = "2026-06-14"
        val to = "2026-06-14"
        val sortBy = "popularity"
        val apiKey = "eb0c87479cef41298b0f5948e67f88b9"

        viewModelScope.launch {
            try {
                val response = ApiConfig.apiService.getCurrentData(
                    q = q,
                    from = from,
                    to = to,
                    sortBy = sortBy,
                    apiKey = apiKey
                )
                news.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}