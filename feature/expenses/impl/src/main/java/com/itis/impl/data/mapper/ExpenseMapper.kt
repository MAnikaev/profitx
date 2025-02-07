package com.itis.impl.data.mapper


import com.itis.api.model.Category
import com.itis.api.model.Expense
import com.itis.core.network.request.ExpenseRequest
import com.itis.core.network.response.CategoryResponse
import com.itis.core.network.response.ExpenseResponse


class ExpenseMapper {

    private fun mapCategoryResponseToModel(response: CategoryResponse) : Category =
        Category(
            id = response.id,
            name = response.name,
            color = response.color
        )

    fun mapExpenseResponseToModel(response: ExpenseResponse) : Expense =
        Expense(
            id = response.id,
            value = response.value,
            name = response.name,
            date = response.date,
            category = mapCategoryResponseToModel(response.category)
        )

    fun mapExpenseToRequest(expense: Expense) : ExpenseRequest =
        ExpenseRequest(
            name = expense.name,
            value = expense.value,
            categoryName = expense.category.name
        )

}