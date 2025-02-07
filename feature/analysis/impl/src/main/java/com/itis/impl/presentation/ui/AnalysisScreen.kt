import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.itis.impl.presentation.model.AnalysisState
import com.itis.impl.presentation.ui.AnalysisViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

@Composable
fun AnalysisScreen(
    viewModel: AnalysisViewModel = koinViewModel()
) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFEFF5E6))) {
        Header()
        RecommendationCard(viewModel)
        TransactionsList(viewModel)
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD5E8C0))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Анализ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun RecommendationCard(viewModel: AnalysisViewModel) {

    val state = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFD5E8C0))
            .padding(16.dp)
    ) {
        Text("Рекомендация", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        when(state.value) {
            is AnalysisState.DataLoaded -> {
                val data = (state.value as AnalysisState.DataLoaded).transactions

                val expenseCategoryNames = data.filter { t ->
                    t.categoryId >= 7 || t.categoryId == 5
                }.groupBy {
                    t -> t.categoryName
                }.map { kvp ->
                    Pair(kvp.key, kvp.value.sumOf { t -> t.value })
                }.sortedBy { p ->
                    p.second
                }.map { p ->
                    p.first
                }.take(2)

                if (expenseCategoryNames.size == 1)
                    Text("Вам стоит уменьшить расходы в категориях: ${expenseCategoryNames[0]}")
                else if (expenseCategoryNames.size == 2)
                    Text("Вам стоит уменьшить расходы в категориях: ${expenseCategoryNames[0]}, ${expenseCategoryNames[1]}")
                else
                    Text("Расходов еще не было")


            }

            AnalysisState.Initial -> {
                // ignored
            }
        }


    }
}

@Composable
fun TransactionsList(viewModel: AnalysisViewModel) {

    val state = viewModel.uiState.collectAsState()

    when(state.value) {
        is AnalysisState.Initial -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is AnalysisState.DataLoaded -> {

            var data = (state.value as AnalysisState.DataLoaded).transactions

            for (i in data.indices) {
                if (isExpense(data[i].categoryId))
                    data[i].value *= -1
                data[i].date = data[i].date.slice(0 until 10)
                val year = data[i].date.slice(0..3)
                val month = data[i].date.slice(5..6)
                val day = data[i].date.slice(8..9)
                data[i].date = "$day.$month.$year"
            }

            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

            data = data.sortedBy {
                LocalDate.parse(it.date, formatter)
            }

            val groupedData = data.groupBy { it.date }

            LazyColumn(modifier = Modifier.padding(16.dp)) {
                groupedData.forEach { (date, transactions) ->
                    item {
                        Text(
                            text = date,
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    items(transactions) { item ->
                        TransactionItem(
                            category = item.name,
                            amount = item.value.toString(),
                            color = Color(android.graphics.Color.parseColor(item.categoryColor))
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TransactionItem(date: String = "", category: String, amount: String, color: Color) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (date.isNotEmpty()) {
            Text(date, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(color, RoundedCornerShape(50))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(category, modifier = Modifier.weight(1f))
            Text(amount, fontWeight = FontWeight.Bold)
        }
    }
}

private fun isExpense(categoryId: Int) : Boolean = categoryId >= 7
