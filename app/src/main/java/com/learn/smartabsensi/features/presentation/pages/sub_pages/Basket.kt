package com.learn.smartabsensi.features.presentation.pages.sub_pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.learn.smartabsensi.features.data.models.FoodModel
import com.learn.smartabsensi.features.presentation.components.TopBar

@Composable
fun Basket(
    selectedFood: MutableList<FoodModel>,
    previousScreen: () -> Unit
) {
    Scaffold(
        topBar = {
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

        }
    }
}