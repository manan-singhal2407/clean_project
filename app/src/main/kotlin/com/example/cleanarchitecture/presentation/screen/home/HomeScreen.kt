package com.example.cleanarchitecture.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cleanarchitecture.presentation.theme.atom.HomeCard

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeCard(text = "Add new match", onClick = {
        })

        HomeCard(text = "Upcoming Matches", onClick = {
        })

        HomeCard(text = "Completed Matches", onClick = {
        })

        HomeCard(text = "Private contest", onClick = {
        })

        HomeCard(text = "Prize Breakup Details", onClick = {
        })

        HomeCard(text = "Team Details", onClick = {
        })

        HomeCard(text = "Version Details", onClick = {
            homeViewModel.navigateToVersionScreen()
        })
    }
}