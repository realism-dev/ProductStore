package dev.realism.productstore.productlistscreen.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.realism.productstore.R
import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.ui.theme.Blue40
import dev.realism.productstore.ui.theme.Gray40
import dev.realism.productstore.ui.theme.Gray80
import dev.realism.productstore.ui.theme.LightGray40
import dev.realism.productstore.ui.theme.LightPurple40
import dev.realism.productstore.ui.theme.Orange40
import dev.realism.productstore.ui.theme.Purple
import dev.realism.productstore.ui.theme.RemoveDialogBackgroundColor
import dev.realism.productstore.ui.theme.RemoveDialogDismissTextColor
import dev.realism.productstore.ui.theme.Violet40
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen (viewModel: ProductListScreenViewModel) {
    val focusManager = LocalFocusManager.current
    val productList by viewModel.getAllProductItems().collectAsState(initial = emptyList())
    Scaffold(
        modifier = Modifier
            .background(Blue40)
            .pointerInput(Unit) {
                // Отслеживаем тап вне поля ввода, чтобы снимать фокус
                detectTapGestures {
                    focusManager.clearFocus()
                }
            },
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Blue40,
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(Blue40)
                            .fillMaxWidth()
                            .height(height = 75.dp)
                    ) {
                        Text(
                            text = "Список товаров",
                            fontSize = 22.sp,
                            modifier = Modifier
                        )
                    }
                }
            )
        },
        bottomBar = {
            Row (
                modifier = Modifier
                    .background(Blue40)
                    .fillMaxWidth()
                    .height(height = 45.dp)
            ) {}
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                ProductSearchItem()
            }
            items(productList) { productItem ->
                ProductListItem(productItem)
            }
        }

    }
}

fun convertEpochToDate(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()) // Форматирование
    format.timeZone = TimeZone.getDefault() // Устанавливаем текущий часовой пояс
    val formattedDate = format.format(date)
    return formattedDate.toString()
}


fun getTagsCount(tags: String): Int {
    val pattern = Pattern.compile("\\[(.*?)]") // Ищем текст внутри []
    val matcher = pattern.matcher(tags)
    var result = 0
    while (matcher.find()) {
        matcher.group(1)?.let { result++ }
    }
    return result
}
var tagCounts = 0
fun getTagList(tags: String): List<String> {
    val pattern = Pattern.compile("\"(.*?)\"") // Ищем текст внутри двойных кавычек
    val matcher = pattern.matcher(tags.substring(1,tags.length-1))
    val result = mutableListOf<String>()
    while (matcher.find()) {
        matcher.group(1)?.let { result.add(it) } // Добавляем текст без кавычек
    }
    tagCounts++

    Log.d("VIEWMODEL","Запросов getTagList: $tagCounts")
    return result
}

@Composable
fun ProductSearchItem() {
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
                placeholder = {
                    val searchHint = if (isFocused) "" else "Поиск товаров"
                    Text(
                        text = searchHint,
                        style = TextStyle(color = Color.DarkGray, fontSize = 16.sp)
                    )
                },
                onValueChange = {
                    searchText = it
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
                        contentDescription = "Search product",
                        tint = Color.DarkGray
                    )
                },
                trailingIcon = {
                    if(searchText.isNotEmpty()){
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Clear text",
                            tint = Color.DarkGray,
                            modifier = Modifier.clickable {
                                searchText = ""
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
                            text = "Поиск товаров",
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductListItem(productItem: ProductItem) {
    var showEditDialog by remember { mutableStateOf(false) }
    var showRemoveDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray40)
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 10.dp,
                bottom = 10.dp
            )
    ) {
        Column(
            modifier = Modifier
                .border(1.dp, Gray40, RoundedCornerShape(5.dp))
                .shadow(4.dp, RoundedCornerShape(8.dp), spotColor = Gray80) // Добавление тени
                .background(LightGray40)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    textAlign = TextAlign.Left,
                    text = productItem.name,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            start = 10.dp
                        )
                        .weight(1f)
                )
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit",
                    tint = Violet40,
                    modifier = Modifier
                        .clickable { showEditDialog = true }
                        .padding(
                            top = 16.dp,
                            end = 15.dp
                        )
                )
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = Orange40,
                    modifier = Modifier.padding(
                        top = 16.dp,
                        end = 16.dp
                    )
                        .clickable {
                            showRemoveDialog = true
                        }
                )
            }
            FlowRow(
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp)
                    .fillMaxWidth(),
            ) {
                getTagList(productItem.tags).forEach { tag ->
                    val interactionSource = remember { MutableInteractionSource() }
                    Box(
                        modifier = Modifier
                            .background(LightGray40)
                            .padding(end = 5.dp, bottom = 5.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = ripple()
                            ) {}
                            .border(1.dp, Gray80, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tag,
                            maxLines = 1,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W500,
                            modifier = Modifier
                                .padding(start = 15.dp, end = 15.dp, top = 4.dp, bottom = 4.dp)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 5.dp, start = 10.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "На складе",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = "Дата добавления",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .padding(end = 50.dp)
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 1.dp, bottom = 12.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = if (productItem.amount == 0) "Нет в наличии" else productItem.amount.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(1f)
                )
                Text(
                    text = convertEpochToDate(productItem.time),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(end = 100.dp)
                )
            }
        }
    }
    if (showEditDialog) EditDialog(productItem) { showEditDialog = false }
    if (showRemoveDialog) RemoveDialog(productItem) { showRemoveDialog = false }
}

@Composable
fun EditDialog(productItem: ProductItem, onDismiss: () -> Unit) {
    AlertDialog(
        containerColor = LightPurple40,
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    "Отмена",
                    color = Purple
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    "Принять",
                    color = Purple
                )
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Products count",
                tint = Color.DarkGray,
                modifier = Modifier.padding(
                    end = 0.dp
                )
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Количество товара",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.RemoveCircleOutline,
                        contentDescription = "Products count",
                        tint = Purple,
                        modifier = Modifier
                            .scale(1.4f)
                    )
                    Text(
                        text = productItem.amount.toString(),
                        fontSize = 25.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.W400,
                        modifier = Modifier.padding(
                            start = 30.dp,
                            end = 30.dp
                        )
                    )
                    Icon(
                        imageVector = Icons.Filled.AddCircleOutline,
                        contentDescription = "Products count",
                        tint = Purple,
                        modifier = Modifier
                            .scale(1.5f)
                    )
                }
            }
        }
    )
}

@Composable
fun RemoveDialog(productItem: ProductItem, onDismiss: () -> Unit) {
    AlertDialog(
        containerColor = RemoveDialogBackgroundColor,
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    "Нет",
                    color = RemoveDialogDismissTextColor
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    "Да",
                    color = RemoveDialogDismissTextColor
                )
            }
        },
        icon = {
            Image(
                painter = painterResource(R.drawable.ic_round_warning_24), // Передаем painter
                contentDescription = "Products count"
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Удаление товара",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Text(
                    text = "Вы действительно хотите удалить выбранный товар?",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                )

            }
        }
    )
}

@Preview
@Composable
fun ProductListScreenPreview() {
//    ProductListScreen()
}