package com.itis.impl.presentation.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.itis.impl.presentation.model.ProfileAction
import com.itis.impl.presentation.model.ProfileEvent
import com.itis.impl.presentation.model.ProfileState
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = koinViewModel()
) {

    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.actionsFlow.collectLatest {
            when(it) {
                ProfileAction.NavigateToAuthorization -> {
                    navController.navigate("login") {
                        popUpTo("main") { inclusive = true }
                    }
                }
                ProfileAction.ShowHistoryClearedSnackBar -> {

                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(state.value) {
            is ProfileState.DataLoaded -> {
                BasicTextField(
                    value = (state.value as ProfileState.DataLoaded).email,
                    onValueChange = {},
                    enabled = false,
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                    modifier = Modifier
                        .border(1.dp, Color.LightGray, shape = MaterialTheme.shapes.small)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
            is ProfileState.Initial -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }


        Spacer(modifier = Modifier.height(24.dp))

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { viewModel.obtainEvent(ProfileEvent.ClearHistoryButtonClicked) },
            colors = ButtonDefaults.buttonColors(Color(0xFFD8EECF)),
            modifier = Modifier.fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Очистить историю", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.obtainEvent(ProfileEvent.ExitButtonClicked) },
            colors = ButtonDefaults.buttonColors(Color(0xFFD8EECF)),
            modifier = Modifier.fillMaxWidth()
                .height(48.dp)
            ) {
            Text(text = "Выйти", color = Color.Black)
        }
    }
}

