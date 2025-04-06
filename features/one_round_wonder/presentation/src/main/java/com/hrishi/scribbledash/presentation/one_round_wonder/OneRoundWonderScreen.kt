package com.hrishi.scribbledash.presentation.one_round_wonder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrishi.scribbledash.components.ScribbleDashTopBar
import com.hrishi.scribbledash.designsystem.CloseIcon
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.backgroundGradient
import com.hrishi.scribbledash.designsystem.componentDimensions
import com.hrishi.scribbledash.designsystem.onBackgroundVariant
import com.hrishi.scribbledash.designsystem.spacing
import com.hrishi.scribbledash.domain.model.common.DifficultySetting
import com.hrishi.scribbledash.presentation.model.DifficultySettingUi
import com.hrishi.ui.ObserveAsEvents
import com.scribbledash.presentation.ui.R.string
import org.koin.androidx.compose.koinViewModel
import kotlin.enums.EnumEntries

@Composable
fun OneRoundWonderScreenRoot(
    modifier: Modifier = Modifier,
    onNavigateToDrawingScreen: (DifficultySetting) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: OneRoundWonderViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is OneRoundWonderEvent.NavigateToDrawingScreen -> onNavigateToDrawingScreen(event.difficultySetting)
            OneRoundWonderEvent.NavigateBack -> onNavigateBack()
        }
    }

    OneRoundWonderScreen(
        modifier = modifier,
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Composable
fun OneRoundWonderScreen(
    modifier: Modifier = Modifier,
    uiState: OneRoundWonderViewState,
    onAction: (OneRoundWonderAction) -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .background(brush = MaterialTheme.colorScheme.backgroundGradient)
            .statusBarsPadding(),
        topBar = {
            ScribbleDashTopBar(
                icon = CloseIcon,
                onIconClick = {
                    onAction(OneRoundWonderAction.OnCloseClick)
                }
            )
        },
    ) { paddingValues ->
        DifficultySelectionContent(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            difficultySettings = uiState.difficultySettings,
            onDifficultySelected = { difficulty ->
                onAction(OneRoundWonderAction.OnDifficultOptionClicked(difficulty))
            }
        )
    }
}

@Composable
private fun DifficultySelectionContent(
    modifier: Modifier = Modifier,
    difficultySettings: EnumEntries<DifficultySettingUi>,
    onDifficultySelected: (DifficultySettingUi) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge7))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(string.start_drawing),
            style = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Text(
            text = "Choose a difficulty setting",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackgroundVariant
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge4))

        DifficultyOptionsRow(
            difficultySettings = difficultySettings.toList(),
            onDifficultySelected = onDifficultySelected
        )
    }
}

@Composable
private fun DifficultyOptionsRow(
    difficultySettings: List<DifficultySettingUi>,
    onDifficultySelected: (DifficultySettingUi) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(difficultySettings) { difficulty ->
            val topPadding = when (difficulty) {
                DifficultySettingUi.CHALLENGING -> MaterialTheme.spacing.none
                else -> MaterialTheme.spacing.medium
            }

            val endPadding = when (difficulty) {
                DifficultySettingUi.MASTER -> MaterialTheme.spacing.none
                else -> MaterialTheme.spacing.extraLarge
            }

            DifficultySettingItem(
                modifier = Modifier
                    .padding(top = topPadding, end = endPadding),
                difficulty = difficulty,
                onClick = onDifficultySelected
            )
        }
    }
}

@Composable
private fun DifficultySettingItem(
    modifier: Modifier = Modifier,
    difficulty: DifficultySettingUi,
    onClick: (DifficultySettingUi) -> Unit,
) {
    Column(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick(difficulty) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(difficulty.iconRes),
            contentDescription = stringResource(difficulty.displayNameResId),
            modifier = Modifier
                .size(MaterialTheme.componentDimensions.difficultIconSize)
                .shadow(MaterialTheme.componentDimensions.smallShadow, shape = CircleShape),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallMedium))

        Text(
            text = stringResource(difficulty.displayNameResId),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackgroundVariant
        )
    }
}

@Preview
@Composable
fun PreviewOneRoundWonderScreen() {
    ScribbleDashTheme {
        OneRoundWonderScreen(
            uiState = OneRoundWonderViewState(),
            onAction = {}
        )
    }
}