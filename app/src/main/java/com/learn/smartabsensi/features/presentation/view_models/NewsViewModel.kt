package com.learn.smartabsensi.features.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.smartabsensi.features.data.models.NewsModel
import com.learn.smartabsensi.features.data.repositories.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()

    var newsList by mutableStateOf<List<NewsModel>>(emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadNews() {
        viewModelScope.launch {
            val result = repository.getNews()
            result.onSuccess {
                newsList = it
            }.onFailure {
                errorMessage = it.message
            }
        }
    }
}