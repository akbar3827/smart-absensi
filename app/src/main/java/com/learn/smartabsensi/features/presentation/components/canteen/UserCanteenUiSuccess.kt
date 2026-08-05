package com.learn.smartabsensi.features.presentation.components.canteen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learn.smartabsensi.R
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.data.models.FoodModel
import com.learn.smartabsensi.features.data.models.UserModel
import com.learn.smartabsensi.features.presentation.components.FoodCard
import com.learn.smartabsensi.features.presentation.components.TopBar
import com.learn.smartabsensi.features.presentation.view_models.CanteenViewModel
import kotlinx.coroutines.delay

@Composable
fun UserCanteenUiSuccess(
    modifier: Modifier = Modifier,
    onNotificationPageClick: (UserModel) -> Unit,
    cvm: CanteenViewModel,
    user: UserModel,
    foods: List<FoodModel>
) {
    val searchValue by cvm.search.collectAsStateWithLifecycle()
    val selectedType by cvm.typeFood.collectAsStateWithLifecycle()
    val typeFood = remember {
        listOf(
            "Semua",
            "Makanan",
            "Minuman",
            "Cemilan",
            "Promo"
        )
    }

    val isFocused = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        cvm.onTypeFoodChanged(typeFood[0])
    }
    LaunchedEffect(key1 = searchValue) {
        delay(1500)
        cvm.getFood()
    }
    LaunchedEffect(key1 = selectedType) {
        cvm.getFood()
    }

    Scaffold(
        topBar = {
            TopBar(
                user = user,
                onNotificationPageClick = onNotificationPageClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .padding(top = 30.dp)
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

            Spacer(Modifier.height(20.dp))

            SearchBar(
                modifier = Modifier.padding(bottom = 10.dp),
                value = searchValue,
                isFocused = isFocused
            ) {
                cvm.onSearchChanged(it)
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(typeFood.size) {
                    val focus = typeFood[it] == selectedType

                    Text(
                        text = typeFood[it],
                        color = if (focus) Color.White else TextSecondary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .shadow(
                                elevation = 4.dp,
                                clip = false,
                                shape = CircleShape,
                                ambientColor = TextSecondary,
                                spotColor = TextSecondary.copy(alpha = 0.4f)
                            )
                            .background(
                                color = if (focus) Indigo
                                else Color.White,
                                shape = CircleShape
                            )
                            .padding(vertical = 10.dp, horizontal = 14.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                cvm.onTypeFoodChanged(typeFood[it])
                            }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = if (selectedType == typeFood[0]) "Semua Menu" else selectedType,
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (foods.size == 0) {
                    item(span = { GridItemSpan(2) }) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.search_no_result),
                                contentDescription = "Search no result",
                                modifier = Modifier.size(200.dp)
                            )
                        }
                    }
                } else {
                    items(foods.size) {
                        FoodCard(foodModel = foods[it])
                    }
                }
            }
        }
    }
}