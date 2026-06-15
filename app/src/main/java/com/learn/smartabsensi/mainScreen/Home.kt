package com.learn.smartabsensi.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.smartabsensi.R
import com.learn.smartabsensi.ui.theme.Background
import com.learn.smartabsensi.ui.theme.Primary
import com.learn.smartabsensi.ui.theme.PrimaryDark
import com.learn.smartabsensi.ui.theme.TextPrimary
import com.learn.smartabsensi.ui.theme.TextSecondary
import com.learn.smartabsensi.ui.theme.backgroundIcon
import com.learn.smartabsensi.viewModel.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    hvm: HomeViewModel
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopBar(modifier = Modifier.fillMaxWidth())
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
                Text(
                    text = buildAnnotatedString {
                        append("Hello, ")
                        withStyle(
                            style = SpanStyle(
                                color = Primary
                            )
                        ) {
                            append("Budi Santoso!")
                        }
                    },
                    color = TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                Text(
                    text = "Do you ready for study today? Don't forget to present okay!",
                    color = TextSecondary
                )
                Spacer(Modifier.height(32.dp))
                Absensi(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(300.dp)
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(6.dp).fillMaxWidth().height(40.dp)
                ) {
                    Text(
                        text = "Berita terbaru",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = "Lihat semua",
                        color = Primary
                    )
                }
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                }
            }
        }
    }
}


@Composable
fun TopBar(
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.account),
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "SmartAbsen",
                color = PrimaryDark,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bell),
                contentDescription = "bell",
                tint = PrimaryDark,
                modifier = Modifier.size(25.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile_pict),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
fun Absensi(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(24.dp),
                clip = false,
                ambientColor = Color.Black.copy(alpha = 0.2f),
                spotColor = Color.Black.copy(alpha = 0.2f)
            )
            .background(Color.White, shape = RoundedCornerShape(24.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(backgroundIcon, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )
        }
        Spacer(Modifier.height(24.dp))
        Text(
            text = "SMA Muhammadiyah 3 Jember",
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "Dalam jangkauan absensi",
            color = Primary
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {

            },
            modifier = Modifier.padding(horizontal = 28.dp).fillMaxWidth().height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary.copy(alpha = 0.7f),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Absent now",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun CardNews(
    modifier: Modifier,

) {

}