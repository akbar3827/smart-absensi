package com.learn.smartabsensi.features.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.learn.smartabsensi.features.data.models.FoodFavModel
import com.learn.smartabsensi.features.data.models.FoodModel
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.data.repositories.FoodRepository
import com.learn.smartabsensi.features.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CanteenViewModel @Inject constructor(
    val foodRepo: FoodRepository,
    val auth: FirebaseAuth,
    val userRepo: UserRepository
) : ViewModel() {

    val uid = auth.uid ?: ""

    private val _user =
        MutableStateFlow<CanteenUserUiState>(CanteenUserUiState.isLoading)
    val user = _user.asStateFlow()

    private val _food =
        MutableStateFlow<CanteenFoodUiState>(CanteenFoodUiState.isLoading)
    val food = _food.asStateFlow()

    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()
    fun onSearchChanged(string: String) {
        _search.value = string
    }

    private val _typeFood = MutableStateFlow("")
    val typeFood = _typeFood.asStateFlow()
    fun onTypeFoodChanged(string: String) {
        _typeFood.value = string
    }

    private val _selectedFood =
        MutableStateFlow<FoodModel?>(null)
    val selectedFood = _selectedFood.asStateFlow()
    fun selectedFoodChanged(v: FoodModel) {
        _selectedFood.value = v
    }

    private val _bottomBarShowed =
        MutableStateFlow(false)
    val bottomBarShowed = _bottomBarShowed.asStateFlow()
    fun bottomBarShowedChanged(v: Boolean) {
        _bottomBarShowed.value = v
    }

    private val _orderQuantity =
        MutableStateFlow(1)
    val orderQuantity = _orderQuantity.asStateFlow()
    fun orderQuantityChanged(v: Int) {
        _orderQuantity.value = v
    }

    init {
        getFood()
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            val result = userRepo.getUserData(uid)

            result.onSuccess { user ->
                _user.update { CanteenUserUiState.Success(user) }
            }.onFailure { throwable ->
                _user.update { CanteenUserUiState.Error(throwable.message ?: "") }
            }
        }
    }

    fun updateFavoriteFood(
        food: FoodModel
    ) {
        val updateData = mutableMapOf<String, Any>(
            "favoriteFood" to FoodFavModel(id = food.id, name = food.name)
        )

        viewModelScope.launch {
            userRepo.updateUserData(
                uid = uid,
                data = updateData
            )
        }
    }

    fun getFood() {
        viewModelScope.launch {
            val result = foodRepo.getFood(
                search = _search.value,
                typeFood = _typeFood.value
            )

            result.onSuccess { foods ->
                _food.update { CanteenFoodUiState.Success(foods) }
            }.onFailure { throwable ->
                _food.update { CanteenFoodUiState.Error(throwable.message ?: "") }
            }
        }
    }
}

sealed interface CanteenUserUiState {
    object isLoading : CanteenUserUiState
    data class Success(val user: UserModel) : CanteenUserUiState
    data class Error(val message: String) : CanteenUserUiState
}

sealed interface CanteenFoodUiState {
    object isLoading : CanteenFoodUiState
    data class Success(val food: List<FoodModel>) : CanteenFoodUiState
    data class Error(val message: String) : CanteenFoodUiState
}
