package com.learn.smartabsensi.features.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.smartabsensi.features.data.models.FoodModel
import com.learn.smartabsensi.features.data.repositories.FoodRepository
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {
    private val foodRepository = FoodRepository()

    var foodList by mutableStateOf<List<FoodModel>>(emptyList())
        private set
    var isError by mutableStateOf<String?>(null)
        private set
    fun loadFood() {
        viewModelScope.launch {
            val result = foodRepository.getFood()
            result.onSuccess {
                foodList = it
            }
            result.onFailure {
                isError = it.message
            }
        }
    }
}