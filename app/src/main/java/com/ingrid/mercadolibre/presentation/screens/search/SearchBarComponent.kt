package com.ingrid.mercadolibre.presentation.screens.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ingrid.mercadolibre.presentation.core.TextStyleBoldComponent
import com.ingrid.mercadolibre.ui.theme.GrayML
import com.ingrid.mercadolibre.ui.theme.YellowML

@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    showSearch: Boolean = true,
    searchText: String,
    placeholder: String = "",
    tint: Color = Color.Yellow,
    onSearchTextChanged: (String) -> Unit = {},
    onClearClick: () -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onDelete: (Boolean) -> Unit = {}
) {
    var showClearButton by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    var isFocused by rememberSaveable { mutableStateOf(false) }
    var searchTextChanged by remember { mutableStateOf("") }
    LaunchedEffect(true) {
        if (isFocused) {
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = Modifier
            .background(YellowML)
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
    ) {
        if (showSearch) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color.White)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            showClearButton = (focusState.isFocused)
                            isFocused = focusState.isFocused
                        }
                        .focusRequester(focusRequester),
                    value = searchText,
                    onValueChange = {
                        onSearchTextChanged(it)
                        searchTextChanged = it
                    },

                    placeholder = {
                        TextStyleBoldComponent(
                            textStyleSimple = placeholder,
                            fontSize = 12.sp,
                            modifier = modifier,
                            textAlign = TextAlign.Center,
                            tint = GrayML
                        )
                    },
                    leadingIcon = {
                        IconButton(
                            onClick = {
                                onSearchClick(searchTextChanged)
                            },
                            modifier = Modifier
                                .clip(
                                    shape = RoundedCornerShape(
                                        topStart = 8.dp,
                                        topEnd = 0.dp,
                                        bottomEnd = 0.dp,
                                        bottomStart = 8.dp
                                    ),
                                )
                                .background(color = Color.White)
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "search",
                                tint = YellowML
                            )
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = GrayML,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = Color.Transparent,
                        unfocusedLabelColor = Color.Transparent,
                        errorLabelColor = Color.Transparent,
                        disabledLabelColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent,
                    ),
                    trailingIcon = {
                        Column {
                            AnimatedVisibility(
                                visible = showClearButton,
                                enter = fadeIn(),
                                exit = fadeOut()
                            ) {

                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "limpiar busqueda",
                                    modifier = Modifier.clickable {
                                        onClearClick()
                                    },
                                    tint = GrayML.copy(alpha = 0.5f)
                                )

                            }
                        }

                    },
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                    })
                )
            }
        }
        Row(Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint = GrayML
            )
            TextStyleBoldComponent(
                textStyleSimple = "Suba",
                fontSize = 12.sp,
                modifier = modifier,
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
                tint = GrayML,
                contentDescription = null
            )
        }
    }

}
