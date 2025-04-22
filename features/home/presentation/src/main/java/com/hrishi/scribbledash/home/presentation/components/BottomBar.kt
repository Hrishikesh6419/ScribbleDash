package com.hrishi.scribbledash.home.presentation.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.hrishi.scribbledash.designsystem.ChartIcon
import com.hrishi.scribbledash.designsystem.HomeIcon
import com.hrishi.scribbledash.designsystem.componentDimensions

enum class BottomBarTab {
    STATISTICS, HOME
}

data class BottomNavItem(
    val tab: BottomBarTab,
    val icon: ImageVector,
    val iconColor: Color,
    val contentDescription: String
)

@Composable
fun BottomBar(
    selectedTab: BottomBarTab,
    onTabSelected: (BottomBarTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem(
            tab = BottomBarTab.STATISTICS,
            icon = ChartIcon,
            iconColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentDescription = "Statistics"
        ),
        BottomNavItem(
            tab = BottomBarTab.HOME,
            icon = HomeIcon,
            iconColor = MaterialTheme.colorScheme.primary,
            contentDescription = "Home"
        )
    )

    NavigationBar(
        modifier = modifier.heightIn(max = MaterialTheme.componentDimensions.bottomBarHeight)
            .shadow(MaterialTheme.componentDimensions.mediumShadow),
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(MaterialTheme.componentDimensions.bottomBarIconSize),
                        imageVector = item.icon,
                        contentDescription = item.contentDescription
                    )
                },
                selected = selectedTab == item.tab,
                onClick = { onTabSelected(item.tab) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = item.iconColor,
                    unselectedIconColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                    indicatorColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            )
        }
    }
}