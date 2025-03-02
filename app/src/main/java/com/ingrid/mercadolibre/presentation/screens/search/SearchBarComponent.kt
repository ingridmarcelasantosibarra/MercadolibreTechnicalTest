package com.ingrid.mercadolibre.presentation.screens.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import com.ingrid.mercadolibre.ui.theme.gray10
import com.ingrid.mercadolibre.ui.theme.yellow

@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
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
            .background(yellow)
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
    ) {
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
                    Text(
                        text = placeholder,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                        ),
                        color = gray10
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
                        androidx.compose.material3.Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "search",
                            tint = yellow
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = gray10,
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

                                androidx.compose.material3.Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "limpiar busqueda",
                                    tint = gray10.copy(alpha = 0.5f)
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
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
