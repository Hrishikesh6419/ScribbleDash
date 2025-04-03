package com.hrishi.scribbledash.home.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.hrishi.scribbledash.designsystem.ChartIcon
import com.hrishi.scribbledash.designsystem.HomeIcon

enum class BottomBarTab {
    FUTURE, HOME
}

data class BottomNavItem(
    val tab: BottomBarTab,
    val icon: ImageVector,
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
            tab = BottomBarTab.FUTURE,
            icon = ChartIcon,
            contentDescription = "Future Destination"
        ),
        BottomNavItem(
            tab = BottomBarTab.HOME,
            icon = HomeIcon,
            contentDescription = "Home"
        )
    )

    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.contentDescription
                    )
                },
                selected = selectedTab == item.tab,
                onClick = { onTabSelected(item.tab) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                    indicatorColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            )
        }
    }
}