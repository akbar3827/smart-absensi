package com.learn.smartabsensi.mainScreen

import androidx.compose.runtime.Composable
import com.learn.smartabsensi.viewModel.HistoryViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun History(
    navigator: DestinationsNavigator,
    hvm: HistoryViewModel
) {

}