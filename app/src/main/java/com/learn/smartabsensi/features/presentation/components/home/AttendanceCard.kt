package com.learn.smartabsensi.features.presentation.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.learn.smartabsensi.core.themes.Amber
import com.learn.smartabsensi.core.themes.Coral
import com.learn.smartabsensi.core.themes.DarkIndigo
import com.learn.smartabsensi.core.themes.Orange
import com.learn.smartabsensi.core.themes.Purple
import com.learn.smartabsensi.core.themes.Teal
import com.learn.smartabsensi.features.data.models.AttendanceModel
import com.learn.smartabsensi.features.data.models.StatTypeModel

@Composable
fun AttendanceCard(
    state: List<AttendanceModel>
) {
    var hadir = 0
    var sakit = 0
    var izin = 0
    var dispen = 0
    var alfa = 0
    state.forEach {
        if (it.status.lowercase().trim() == "hadir") {
            hadir += 1
        } else if (it.status.lowercase().trim() == "sakit") {
            sakit += 1
        } else if (it.status.lowercase().trim() == "izin") {
            izin += 1
        } else if (it.status.lowercase().trim() == "dispen") {
            dispen += 1
        } else {
            alfa += 1
        }
    }
    val total = hadir + sakit + izin + dispen + alfa
    val percentage = if (total >= 1) {
        (hadir.toFloat() / total * 100).toInt().toString()
    } else {
        "0"
    }
    val listStat = listOf(
        StatTypeModel(
            category = "HADIR",
            color = Teal,
            stat = "$hadir"
        ),
        StatTypeModel(
            category = "SAKIT",
            color = Amber,
            stat = "$sakit"
        ),
        StatTypeModel(
            category = "IZIN",
            color = Orange,
            stat = "$izin"
        ),
        StatTypeModel(
            category = "DISPEN",
            color = Purple,
            stat = "$dispen"
        ),
        StatTypeModel(
            category = "ALFA",
            color = Coral,
            stat = "$alfa"
        ),
        StatTypeModel(
            category = "KEHADIRAN",
            color = DarkIndigo,
            stat = "$percentage%"
        )
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(listStat.size) {
            StatCard(
                category = listStat[it].category,
                color = listStat[it].color,
                stat = listStat[it].stat,
            )
        }
    }
}