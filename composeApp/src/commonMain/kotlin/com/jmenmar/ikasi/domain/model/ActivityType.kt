package com.jmenmar.ikasi.domain.model

import androidx.compose.ui.graphics.Color
import com.jmenmar.ikasi.ui.Blue
import com.jmenmar.ikasi.ui.Green
import com.jmenmar.ikasi.ui.Orange
import com.jmenmar.ikasi.ui.Pink
import com.jmenmar.ikasi.ui.Yellow
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.book
import ikasi.composeapp.generated.resources.fundamentals
import ikasi.composeapp.generated.resources.headphones
import ikasi.composeapp.generated.resources.listening
import ikasi.composeapp.generated.resources.note_stack
import ikasi.composeapp.generated.resources.oral_expression
import ikasi.composeapp.generated.resources.oral_understanding
import ikasi.composeapp.generated.resources.pencil
import ikasi.composeapp.generated.resources.reading
import ikasi.composeapp.generated.resources.speak
import ikasi.composeapp.generated.resources.speaking
import ikasi.composeapp.generated.resources.theory
import ikasi.composeapp.generated.resources.writing
import ikasi.composeapp.generated.resources.written_expression
import ikasi.composeapp.generated.resources.written_understanding
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class ActivityType(
    val priority: Int,
    val title: StringResource,
    val color: Color,
    val icon: DrawableResource,
    val skill: StringResource,
) {
    THEORY(
        priority = 1,
        title = Res.string.theory,
        color = Pink,
        icon = Res.drawable.note_stack,
        skill = Res.string.fundamentals,
    ),
    SPEAKING(
        priority = 2,
        title = Res.string.speaking,
        color = Orange,
        icon = Res.drawable.speak,
        skill = Res.string.oral_expression,
    ),
    LISTENING(
        priority = 3,
        title = Res.string.listening,
        color = Yellow,
        icon = Res.drawable.headphones,
        skill = Res.string.oral_understanding,
    ),
    READING(
        priority = 4,
        title = Res.string.reading,
        color = Green,
        icon = Res.drawable.book,
        skill = Res.string.written_understanding,
    ),
    WRITING(
        priority = 5,
        title = Res.string.writing,
        color = Blue,
        icon = Res.drawable.pencil,
        skill = Res.string.written_expression,
    ),
}