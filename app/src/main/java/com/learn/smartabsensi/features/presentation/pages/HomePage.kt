package com.learn.smartabsensi.features.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.core.themes.Background
import com.learn.smartabsensi.core.themes.BlueDark
import com.learn.smartabsensi.core.themes.BlueJay
import com.learn.smartabsensi.core.themes.Err
import com.learn.smartabsensi.core.themes.GreenDark
import com.learn.smartabsensi.core.themes.Blue
import com.learn.smartabsensi.core.themes.Primary
import com.learn.smartabsensi.core.themes.RedDark
import com.learn.smartabsensi.core.themes.TextPrimary
import com.learn.smartabsensi.core.themes.TextSecondary
import com.learn.smartabsensi.core.themes.YellowDark
import com.learn.smartabsensi.features.presentation.components.AbsensiCard
import com.learn.smartabsensi.features.presentation.components.FoodCard
import com.learn.smartabsensi.features.presentation.components.NewsCard
import com.learn.smartabsensi.features.presentation.components.StatCard
import com.learn.smartabsensi.features.presentation.components.TopBar
import com.learn.smartabsensi.features.presentation.view_models.FoodViewModel
import com.learn.smartabsensi.features.presentation.view_models.HomeViewModel
import com.learn.smartabsensi.features.presentation.view_models.NewsViewModel
import com.learn.smartabsensi.features.presentation.view_models.UserViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>(start = true)
@Composable
fun HomePage(
    navigator: DestinationsNavigator,
    nvm: NewsViewModel,
    hvm: HomeViewModel,
    fvm: FoodViewModel,
    uvm: UserViewModel
) {
    val uid = "abcde123"
    val news = hvm.news.value
    LaunchedEffect(key1 = Unit) {
        hvm.fetchNews()
        nvm.loadNews()
        fvm.loadFood()
        uvm.loadUser(uid)
    }
    val hadir = uvm.userData.hadir.toIntOrNull() ?: 0
    val sakit = uvm.userData.sakit.toIntOrNull() ?: 0
    val izin = uvm.userData.izin.toIntOrNull() ?: 0
    val dispen = uvm.userData.dispen.toIntOrNull() ?: 0
    val alfa = uvm.userData.alfa.toIntOrNull() ?: 0
    val total = hadir + sakit + izin + dispen + alfa
    val percentage = if (total > 0) {
        ((hadir - sakit - izin - dispen - alfa).toFloat() / total * 100).toInt().toString()
    } else {
        "0"
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopBar()
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(horizontal = 16.dp)
                .padding(padding)
        ) {
            item {
                if (uvm.errorMessage != null) {
                    Text(
                        text = "Error: ${uvm.errorMessage}",
                        color = Err,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                Text(
                    text = "Selamat pagi",
                    color = TextPrimary
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Primary
                            )
                        ) {
                            append(uvm.userData.name)
                        }
                        append("")
                    },
                    color = TextPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Sudah siap belajar hari ini? Jangan lupa absen yaa.",
                    color = TextSecondary
                )
                Spacer(Modifier.height(32.dp))
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    AbsensiCard()
                }
                Spacer(Modifier.height(8.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        StatCard(
                            category = "HADIR",
                            color = GreenDark,
                            stat = uvm.userData.hadir
                        )
                    }
                    item {
                        StatCard(
                            category = "SAKIT",
                            color = BlueDark,
                            stat = uvm.userData.sakit
                        )
                    }
                    item {
                        StatCard(
                            category = "IZIN",
                            color = YellowDark,
                            stat = uvm.userData.izin
                        )
                    }
                    item {
                        StatCard(
                            category = "DISPEN",
                            color = BlueJay,
                            stat = uvm.userData.dispen
                        )
                    }
                    item {
                        StatCard(
                            category = "ALFA",
                            color = RedDark,
                            stat = uvm.userData.alfa
                        )
                    }
                    item {
                        StatCard(
                            category = "KEHADIRAN",
                            color = Blue,
                            stat = "$percentage%"
                        )
                    }
                }
                Spacer(Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Text(
                        text = "Latest news",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = "See all",
                        color = Primary
                    )
                }
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    nvm.newsList.forEach { news ->
                        item {
                            NewsCard(
                                id = news.author,
                                title = news.title,
                                description = news.description,
                                image = news.imageUrl,
                                category = news.category
                            )
                        }
                    }
                    news?.articles?.forEachIndexed { index, news ->
                        if (news != null && index < 10) {
                            item {
                                NewsCard(
                                    id = news.author!!,
                                    title = news.title!!,
                                    description = news.description!!,
                                    image = news.urlToImage!!,
                                    category = "info"
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Text(
                        text = "Favorite menu",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = "Order",
                        color = Primary
                    )
                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    fvm.foodList.forEach { food ->
                        if (food.fav) {
                            item {
                                FoodCard(
                                    foodModel = food
                                )
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(color = Background),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("created by ")
                            withStyle(
                                style = SpanStyle(
                                    color = Primary
                                )
                            ) {
                                append("MOH. AKBAR KURNIAWAN")
                            }
                        },
                        color = TextPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}