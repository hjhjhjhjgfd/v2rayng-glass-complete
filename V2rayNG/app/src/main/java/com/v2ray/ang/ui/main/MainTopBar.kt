package com.v2ray.ang.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.v2ray.ang.R
import com.v2ray.ang.compose.*

/**
 * PLUTONG 2027 — Top Bar with glass effect + search
 */
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
    onDelAllConfig: () -> Unit = {},
    onDelDuplicateConfig: () -> Unit = {},
    onDelInvalidConfig: () -> Unit = {}
) {
    val isDarkTheme = LocalDarkTheme.current

    TopAppBar(
        title = {
            if (showSearch) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    placeholder = { Text("Search servers...", color = Color.Gray) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = glassAccent
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = onSearchClose) {
                            Icon(painterResource(R.drawable.ic_arrow_back_24dp), "Close", tint = glassAccent)
                        }
                    }
                )
            } else {
                Text(
                    text = if (isLoading) "Loading..." else "PlutoNG",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = if (isDarkTheme) glassTextDark else glassTextLight
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    painter = painterResource(R.drawable.ic_menu_24dp),
                    contentDescription = "Menu",
                    tint = glassAccent
                )
            }
        },
        actions = {
            if (!showSearch) {
                IconButton(onClick = { onSearchToggle(true) }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search_24dp),
                        contentDescription = "Search",
                        tint = glassAccent
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .glassPanel(RoundedCornerShape(20.dp), isDarkTheme)
    )
}