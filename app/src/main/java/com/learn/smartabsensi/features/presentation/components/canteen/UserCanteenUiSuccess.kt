package com.learn.smartabsensi.features.presentation.components.canteen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.FoodModel
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.components.FoodCard
import com.learn.smartabsensi.features.presentation.components.TopBar
import com.learn.smartabsensi.features.presentation.view_models.CanteenViewModel

@Composable
fun UserCanteenUiSuccess(
    modifier: Modifier = Modifier,
    onNotificationPageClick: (UserModel) -> Unit,
    cvm: CanteenViewModel,
    user: UserModel,
    foods: List<FoodModel>
) {
    val searchValue by cvm.search.collectAsStateWithLifecycle()
    val isFocused = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                user = user,
                onNotificationPageClick = onNotificationPageClick
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
                .fillMaxSize()
                .background(Background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Column(
                    modifier = Modifier.padding(top = 30.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "Kantin Sekolah",
                        color = TextPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = "Pesan makanan & minuman favoritmu",
                        color = TextSecondary
                    )
                }
            }

            item(span = { GridItemSpan(2) }) {
                SearchBar(
                    modifier = Modifier.padding(bottom = 10.dp),
                    value = searchValue,
                    isFocused = isFocused
                ) {
                    cvm.onSearchChanged(it.lowercase())
                }
            }

            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "Semua Menu",
                    color = TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            if (searchValue.isEmpty()) {
                items(foods.size) {
                    val food = foods[it]
                    FoodCard(foodModel = food)
                }
            } else {
                foods.forEach { food ->
                    if (food.name.lowercase().contains(searchValue)) {
                        item {
                            FoodCard(foodModel = food)
                        }
                    }
                }
            }
        }
    }
}