package com.bookdel.app.Data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Date

data class Book(
    // val image: ImageVector? = null,
    // val author: String? = null,
    // val publishedDate: Date? = null,
    // val category: BookCategory = BookCategory.UNKNOWN,
    val name: String,
    @DrawableRes val imageRes: Int,
    val cost: Int,
)

enum class BookCategory {
    FICTION,
    NON_FICTION,
    BIOGRAPHY_AUTOBIOGRAPHY,
    HISTORY,SCIENCE,
    TECHNOLOGY_ENGINEERING,
    SOCIAL_SCIENCES,
    ARTS_LITERATURE,
    RELIGION_PHILOSOPHY,
    REFERENCE,
    TRAVEL,
    UNKNOWN
}
