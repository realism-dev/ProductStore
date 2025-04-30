package dev.realism.productstore.productlistscreen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.realism.productstore.R
import dev.realism.productstore.ui.theme.Gray40
import dev.realism.productstore.ui.theme.Gray80
import dev.realism.productstore.ui.theme.LightGray40
import dev.realism.productstore.ui.theme.Purple


@Composable
fun ProductSearchItem(viewModel: ProductListScreenViewModel) {
    var searchText by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray40)
            .height(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 18.dp,
                    bottom = 13.dp
                )
                .fillMaxWidth()
        ) {
            TextField(
                value = searchText,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search // Действие на клавише "Done" или "Enter" — "Search"
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.updateProductList(searchText)
                    }
                ),
                placeholder = {
                    val searchHint = if (isFocused) "" else stringResource(R.string.search_hint)
                    Text(
                        text = searchHint,
                        style = TextStyle(color = Color.DarkGray, fontSize = 16.sp)
                    )
                },
                onValueChange = {
                    searchText = it
                    viewModel.updateProductList(searchText)
                },
                modifier = Modifier
                    .border(
                        if (isFocused) 2.dp else 1.dp,
                        if (isFocused) Purple else Gray80,
                        RoundedCornerShape(5.dp)
                    )
                    .shadow(
                        4.dp,
                        RoundedCornerShape(8.dp),
                        spotColor = if (isFocused) Purple else Gray40
                    )
                    .height(55.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search_product_leading_ic_cd),
                        tint = Color.DarkGray
                    )
                },
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = stringResource(R.string.search_product_trailing_ic_cd),
                            tint = Color.DarkGray,
                            modifier = Modifier.clickable {
                                searchText = ""
                                viewModel.updateProductList(searchText)
                            }
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    cursorColor = Purple,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = LightGray40,
                    focusedContainerColor = LightGray40,
                    unfocusedTextColor = Color.DarkGray
                ),
                textStyle = TextStyle(fontSize = 14.sp, lineHeight = 14.sp)
            )

            if (isFocused) {
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .offset(x = 15.dp, y = (-12).dp)
                        .height(IntrinsicSize.Min),
                ) {
                    Box(
                        modifier = Modifier
                            .background(Gray40)
                    ) {
                        Text(
                            text = stringResource(R.string.search_box_label_at_top),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}