package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.Indigo
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.features.presentation.components.RegistFormStepOne
import com.learn.smartabsensi.features.presentation.components.RegistFormStepThree
import com.learn.smartabsensi.features.presentation.components.RegistFormStepTwo
import com.learn.smartabsensi.features.presentation.view_models.RegistViewModel

@Composable
fun RegistPage(
    rvm: RegistViewModel = viewModel(),
    onLoginPageClick: () -> Unit
) {
    val step = remember {
        mutableStateOf(0)
    }
    val targetWeightBar = if(step.value <= 1) step.value * 0.5f else 1f
    val targetWeightBar2 = if (step.value + 1 >= 2) 1f else 0f
    val targetWeightBar3 = if (step.value + 1 >= 3) 1f else 0f

    val animatedWeightBar by animateFloatAsState(
        targetValue = targetWeightBar,
        label = "bar",
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 50
        )
    )
    val animatedWeightBar2 by animateFloatAsState(
        targetValue = targetWeightBar2,
        label = "bar2",
        animationSpec = tween(
            durationMillis = 500
        )
    )
    val animatedWeightBar3 by animateFloatAsState(
        targetValue = targetWeightBar3,
        label = "bar3",
        animationSpec = tween(
            durationMillis = 500
        )
    )

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = "https://i.pinimg.com/1200x/3c/64/01/3c64010a9347ae510af5974406723363.jpg",
            contentDescription = "background",
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(color = Background,
                    shape = RoundedCornerShape(
                        topStartPercent = 14,
                        topEndPercent = 14
                    ))
                .padding(horizontal = 26.dp)
                .padding(top = 50.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Langkah ${step.value + 1} dari 3",
                    fontSize = 15.sp,
                    color = TextPrimary
                )
                Text(
                    text = "Identitas",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
            Spacer(Modifier.height(8.dp))
            Box(
                Modifier
                    .height(5.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Gray.copy(alpha = 0.1f)),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    Modifier
                        .height(5.dp)
                        .fillMaxWidth(animatedWeightBar
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .background(Indigo)

                )
            }
            Spacer(Modifier.height(16.dp))
            Row(
                Modifier
                    .height(5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Indigo)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Gray.copy(alpha = 0.1f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(animatedWeightBar2)
                            .height(4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Indigo)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Gray.copy(alpha = 0.1f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(animatedWeightBar3)
                            .height(4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Indigo)
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            Spacer(Modifier.height(20.dp))
            if (step.value == 0) {
                RegistFormStepOne(
                    onLoginPageClick = onLoginPageClick,
                    rvm = rvm,
                    step = step
                )
            } else if (step.value == 1) {
                RegistFormStepTwo(rvm = rvm, step = step)
            } else {
                RegistFormStepThree(
                    onLoginPageClick = onLoginPageClick,
                    rvm = rvm,
                    step = step
                )
            }
        }
    }
}