package com.itis.impl.data.mapper

import com.itis.api.model.Category
import com.itis.api.model.Income
import com.itis.core.network.request.IncomeRequest
import com.itis.core.network.response.CategoryResponse
import com.itis.core.network.response.IncomeResponse

class IncomeMapper {

    private fun mapCategoryResponseToModel(response: CategoryResponse) : Category =
        Category(
            id = response.id,
            name = response.name,
            color = response.color
        )

    fun mapIncomeResponseToModel(response: IncomeResponse) : Income =
        Income(
            id = response.id,
            value = response.value,
            name = response.name,
            date = response.date,
            category = mapCategoryResponseToModel(response.category)
        )

    fun mapIncomeToRequest(income: Income) : IncomeRequest =
        IncomeRequest(
            name = income.name,
            value = income.value,
            categoryName = income.category.name
        )

}