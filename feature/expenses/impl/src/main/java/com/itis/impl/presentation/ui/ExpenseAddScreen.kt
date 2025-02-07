package com.itis.impl.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.itis.impl.presentation.model.ExpenseAction
import com.itis.impl.presentation.model.ExpenseEvent
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddExpenseScreen(
    navController: NavController,
    viewModel: ExpenseViewModel = koinViewModel()
) {

    var sum by remember { mutableStateOf("") }
    var expenseCategory by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.actionsFlow.collectLatest { action ->
            when(action) {
                is ExpenseAction.NavigateToAllExpensesScreen -> {
                    navController.navigate("expense")
                }

                ExpenseAction.NavigateToAddExpenseScreen -> {}
                ExpenseAction.ShowDataNotFilledSnackBar -> {}
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = sum,
            onValueChange = {
                sum = it
            },
            label = { Text("Сумма") },
            placeholder = { Text("Введите сумму") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val categories = listOf(
                "", "Инвестиции", "Пособия", "Бизнес", "Подарки", "Премии"
            )
            val colors = listOf(
                Color(0xfff90814), Color(0xff00fe41), Color(0xffcbcbcb),
                Color(0xfff91e07), Color(0xff178f3e), Color(0xff4e4e4e),
            )
            val rows = categories.chunked(3)

            var i = 0
            rows.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    row.forEachIndexed { index, category ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .background(colors[i], shape = RoundedCornerShape(12.dp))
                                    .clickable {
                                        expenseCategory = category
                                    }
                            )
                            i++
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = category,
                                style = TextStyle(fontSize = 12.sp),
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text("Название") },
            placeholder = { Text("Введите название траты") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.obtainEvent(
                ExpenseEvent.AddExpenseClicked(
                name = name,
                sum = sum,
                categoryName = expenseCategory
            )) },
            colors = ButtonDefaults.buttonColors(Color(0xFF8BC34A)),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Text(text = "Добавить", color = Color.White, style = TextStyle(fontSize = 16.sp))
        }
    }
}