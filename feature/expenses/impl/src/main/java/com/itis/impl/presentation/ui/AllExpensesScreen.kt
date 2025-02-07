package com.itis.impl.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.itis.api.model.Expense
import com.itis.impl.presentation.model.ExpenseAction
import com.itis.impl.presentation.model.ExpenseEvent
import com.itis.impl.presentation.model.ExpenseState
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExpensesScreen(
    navController: NavController,
    viewModel: ExpenseViewModel = koinViewModel()
) {

    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.actionsFlow.collectLatest { act ->
            when(act) {
                is ExpenseAction.NavigateToAddExpenseScreen -> {
                    navController.navigate("addExpense")
                }

                ExpenseAction.NavigateToAllExpensesScreen -> {}
                ExpenseAction.ShowDataNotFilledSnackBar -> {}
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE5F4DA), shape = MaterialTheme.shapes.medium)
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Расходы",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color(0xFF6D8D5D),
                    lineHeight = 28.sp
                )
            )
        }


        Spacer(modifier = Modifier.height(16.dp))


        when (state.value) {
            is ExpenseState.Initial -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ExpenseState.DataLoaded -> {
                val expenses = (state.value as ExpenseState.DataLoaded).incomes

                if (expenses.isNotEmpty()) {
                    AndroidView(
                        factory = { context ->
                            PieChart(context).apply {
                                description.isEnabled = false
                                isDrawHoleEnabled = true
                                setHoleColor(android.graphics.Color.WHITE)
                                setTransparentCircleAlpha(0)
                                holeRadius = 70f
                                transparentCircleRadius = 72f
                                centerText = "2000p"
                                setCenterTextSize(22f)
                                data = createPieData(expenses)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )

                    TransactionList(expenses)
                } else {
                    Text(
                        text = "Нет расходов",
                        style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { viewModel.obtainEvent(ExpenseEvent.AddExpenseButtonClicked) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF8BC34A)),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Добавить", color = Color.White, style = TextStyle(fontSize = 16.sp))
        }
    }
}

fun createPieData(incomes: List<Expense>): PieData {

    val categories = incomes.groupBy { t ->
        t.category.name
    }.map { nameTransactionPair ->
        Pair(nameTransactionPair.key, nameTransactionPair.value.sumOf { t -> t.value })
    }.sortedBy { p ->
        p.second
    }

    val entries = mutableListOf<PieEntry>()

    for (category in categories) {
        entries.add(
            PieEntry(category.second.toFloat(), category.first)
        )
    }


    val dataSet = PieDataSet(entries, "").apply {
        colors = incomes.map { android.graphics.Color.parseColor(it.category.color) }
        valueTextSize = 0f
    }

    return PieData(dataSet)
}

@Composable
fun TransactionList(transactions: List<Expense>) {

    val categories = transactions.groupBy { t ->
        t.category.name
    }.map { nameTransactionPair ->
        Pair(nameTransactionPair.key, nameTransactionPair.value.sumOf { t -> t.value })
    }.sortedBy { p ->
        p.second
    }

    val categoryColorMap = transactions.map { t ->
        Pair(t.category.name, t.category.color)
    }.toSet().toMap()

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(0.5f),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories.size) { i ->
            TransactionItem(name = categories[i].first, amount = categories[i].second, categoryColor = Color(categoryColorMap[categories[i].first]!!.toColorInt()))
        }
    }
}

@Composable
fun TransactionItem(name: String, amount: Int, categoryColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFC8D6C0), shape = MaterialTheme.shapes.medium)
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(categoryColor, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = name, style = TextStyle(fontSize = 16.sp))
            }
            Text(
                text = amount.toString() + 'p',
                style = TextStyle(fontSize = 16.sp, color = Color.Black)
            )
        }
    }
}


