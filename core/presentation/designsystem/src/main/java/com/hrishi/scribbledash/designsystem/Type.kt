package com.hrishi.scribbledash.designsystem
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.scribbledash.presentation.designsystem.R

val BagelFatOne = FontFamily(
    Font(R.font.bagelfatone_regular, FontWeight.Normal)
)

@OptIn(ExperimentalTextApi::class)
val OutfitRegular = FontFamily(
    Font(
        R.font.outfit_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(400)
        )
    )
)

@OptIn(ExperimentalTextApi::class)
val OutfitMedium = FontFamily(
    Font(
        R.font.outfit_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(500)
        )
    )
)

@OptIn(ExperimentalTextApi::class)
val OutfitSemiBold = FontFamily(
    Font(
        R.font.outfit_variable,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(600)
        )
    )
)

val DisplayLarge = TextStyle(
    fontFamily = BagelFatOne,
    fontSize = 66.sp,
    lineHeight = 80.sp
)

val DisplayMedium = TextStyle(
    fontFamily = BagelFatOne,
    fontSize = 40.sp,
    lineHeight = 44.sp
)

val HeadlineLarge = TextStyle(
    fontFamily = BagelFatOne,
    fontSize = 34.sp,
    lineHeight = 48.sp
)

val HeadlineMedium = TextStyle(
    fontFamily = BagelFatOne,
    fontSize = 26.sp,
    lineHeight = 30.sp
)

val HeadlineSmall = TextStyle(
    fontFamily = BagelFatOne,
    fontSize = 18.sp,
    lineHeight = 26.sp
)

val HeadlineXSmall = TextStyle(
    fontFamily = BagelFatOne,
    fontSize = 14.sp,
    lineHeight = 18.sp
)

val BodyLarge = TextStyle(
    fontFamily = OutfitMedium,
    fontSize = 20.sp,
    lineHeight = 24.sp
)

val BodyMedium = TextStyle(
    fontFamily = OutfitRegular,
    fontSize = 16.sp,
    lineHeight = 24.sp
)

val BodySmall = TextStyle(
    fontFamily = OutfitRegular,
    fontSize = 14.sp,
    lineHeight = 18.sp
)

val LabelXLarge = TextStyle(
    fontFamily = OutfitSemiBold,
    fontSize = 24.sp,
    lineHeight = 28.sp
)

val LabelLarge = TextStyle(
    fontFamily = OutfitSemiBold,
    fontSize = 20.sp,
    lineHeight = 24.sp
)

val LabelMedium = TextStyle(
    fontFamily = OutfitMedium,
    fontSize = 16.sp,
    lineHeight = 24.sp
)

val LabelSmall = TextStyle(
    fontFamily = OutfitSemiBold,
    fontSize = 14.sp,
    lineHeight = 18.sp
)

val Typography = Typography(
    displayLarge = DisplayLarge,
    displayMedium = DisplayMedium,

    headlineLarge = HeadlineLarge,
    headlineMedium = HeadlineMedium,
    headlineSmall = HeadlineSmall,

    bodyLarge = BodyLarge,
    bodyMedium = BodyMedium,
    bodySmall = BodySmall,

    labelLarge = LabelLarge,
    labelMedium = LabelMedium,
    labelSmall = LabelSmall,

    titleLarge = HeadlineLarge,
    titleMedium = HeadlineMedium,
    titleSmall = HeadlineSmall
)

object ExtendedTypography {
    val headlineXSmall = HeadlineXSmall
    val labelXLarge = LabelXLarge
}

val Typography.headlineXSmall: TextStyle
    @Composable
    get() = ExtendedTypography.headlineXSmall

val Typography.labelXLarge: TextStyle
    @Composable
    get() = ExtendedTypography.labelXLarge