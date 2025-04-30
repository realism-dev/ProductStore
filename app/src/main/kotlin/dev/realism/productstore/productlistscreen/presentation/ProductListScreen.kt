package dev.realism.productstore.productlistscreen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.realism.productstore.R
import dev.realism.productstore.ui.theme.Blue40
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen (viewModel: ProductListScreenViewModel) {
    val focusManager = LocalFocusManager.current
    val productList by viewModel.productList.collectAsState()
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
                            text = stringResource(R.string.product_list_text),
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
                ProductSearchItem(viewModel)
            }
            items(productList) { productItem ->
                ProductListItem(productItem, viewModel)
            }
        }

    }
}

fun convertEpochToDate(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    format.timeZone = TimeZone.getDefault()
    val formattedDate = format.format(date)
    return formattedDate.toString()
}

fun getTagList(tags: String): List<String> {
    val pattern = Pattern.compile("\"(.*?)\"")
    val matcher = pattern.matcher(tags.substring(1,tags.length-1))
    val result = mutableListOf<String>()
    while (matcher.find()) {
        matcher.group(1)?.let { result.add(it) }
    }
    return result
}



@Preview
@Composable
fun ProductListScreenPreview() {
//    ProductListScreen()
}