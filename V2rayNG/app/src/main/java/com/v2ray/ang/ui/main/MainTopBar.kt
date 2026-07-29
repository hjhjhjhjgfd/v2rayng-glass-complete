package com.v2ray.ang.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.v2ray.ang.R
import com.v2ray.ang.compose.AppTopBar
import com.v2ray.ang.compose.LocalDarkTheme
import com.v2ray.ang.compose.glassPanel
import com.v2ray.ang.compose.verticalScrollbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    isLoading: Boolean,
    showSearch: Boolean,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearchClose: () -> Unit,
    onSearchToggle: (Boolean) -> Unit,
    onMenuClick: () -> Unit,
    onAction: (MainAction) -> Unit,
    onDelAllConfig: () -> Unit,
    onDelDuplicateConfig: () -> Unit,
    onDelInvalidConfig: () -> Unit
) {
    var showImportMenu by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }
    val importMenuScrollState = rememberScrollState()
    val moreMenuScrollState = rememberScrollState()
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val navBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val maxMenuHeight = LocalConfiguration.current.screenHeightDp.dp - statusBarHeight - navBarHeight - 20.dp
    val isDarkTheme = LocalDarkTheme.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .glassPanel(
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                isDarkTheme = isDarkTheme
            )
    ) {
    AppTopBar(
        title = stringResource(R.string.title_server),
        onBackClick = {},
        isLoading = isLoading,
        isSearchActive = showSearch,
        searchQuery = searchQuery,
        onSearchQueryChange = onSearchQueryChange,
        onSearchClose = onSearchClose,
        searchPlaceholder = stringResource(R.string.menu_item_search),
        containerColor = Color.Transparent,
        navigationIcon = {
            if (showSearch) {
                IconButton(onClick = onSearchClose) {
                    Icon(painterResource(R.drawable.ic_arrow_back_24dp), contentDescription = "Back")
                }
            } else {
                IconButton(onClick = onMenuClick) {
                    Icon(painterResource(R.drawable.ic_menu_24dp), contentDescription = "Menu")
                }
            }
        },
        actions = {
            if (!showSearch) {
                // Quick-access buttons: ping (real delay test) and sort by ping results,
                // directly on the main screen instead of buried inside the "more" menu.
                IconButton(onClick = { onAction(MainAction.TestRealAllServers) }) {
                    Icon(
                        painterResource(R.drawable.ic_ping_24dp),
                        contentDescription = stringResource(R.string.title_real_ping_all_server)
                    )
                }
                IconButton(onClick = { onAction(MainAction.SortByTestResults) }) {
                    Icon(
                        painterResource(R.drawable.ic_sort_24dp),
                        contentDescription = stringResource(R.string.title_sort_by_test_results)
                    )
                }
                IconButton(onClick = { onSearchToggle(true) }) {
                    Icon(painterResource(R.drawable.ic_search_24dp), contentDescription = "filter")
                }
            }
            Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
                IconButton(onClick = { showImportMenu = true }) {
                    Icon(painterResource(R.drawable.ic_add_24dp), contentDescription = "Add")
                }
                DropdownMenu(
                    expanded = showImportMenu,
                    onDismissRequest = { showImportMenu = false },
                    scrollState = importMenuScrollState,
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .heightIn(max = maxMenuHeight)
                        .verticalScrollbar(importMenuScrollState)
                ) {
                    ImportMenuContent(
                        onAction = { action ->
                            showImportMenu = false
                            onAction(action)
                        }
                    )
                }
            }
            Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
                IconButton(onClick = { showMenu = true }) {
                    Icon(painterResource(R.drawable.ic_more_vert_24dp), contentDescription = "More")
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false },
                    scrollState = moreMenuScrollState,
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .heightIn(max = maxMenuHeight)
                        .verticalScrollbar(moreMenuScrollState)
                ) {
                    MoreMenuContent(
                        onAction = { action ->
                            showMenu = false
                            onAction(action)
                        },
                        onDelAllConfig = { showMenu = false; onDelAllConfig() },
                        onDelDuplicateConfig = { showMenu = false; onDelDuplicateConfig() },
                        onDelInvalidConfig = { showMenu = false; onDelInvalidConfig() }
                    )
                }
            }
        }
    )
    }
}
