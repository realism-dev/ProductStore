package dev.realism.productstore.productlistscreen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import dev.realism.productstore.R
import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.ui.theme.Gray40
import dev.realism.productstore.ui.theme.Gray80
import dev.realism.productstore.ui.theme.LightGray40
import dev.realism.productstore.ui.theme.Orange40
import dev.realism.productstore.ui.theme.Violet40
import kotlinx.coroutines.launch


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductListItem(productItem: ProductItem, viewModel: ProductListScreenViewModel) {
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
                .shadow(4.dp, RoundedCornerShape(8.dp), spotColor = Gray80)
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
                    contentDescription = stringResource(R.string.edit_cd),
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
                    contentDescription = stringResource(R.string.delete_cd),
                    tint = Orange40,
                    modifier = Modifier
                        .padding(
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
                    text = stringResource(R.string.on_warehouse_text),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = stringResource(R.string.add_date_text),
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
                    text = if (productItem.amount == 0) stringResource(R.string.count_zero_text) else productItem.amount.toString(),
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
    if (showEditDialog) EditDialog(
        productItem,
        onDismiss = { showEditDialog = false },
        onUpdate = { newProductItem ->
            viewModel.viewModelScope.launch {
                viewModel.updateProductItem(newProductItem)
                showEditDialog = false
            }
        }
    )
    if (showRemoveDialog) RemoveDialog(
        onDismiss = { showRemoveDialog = false },
        onRemove = {
            viewModel.viewModelScope.launch {
                viewModel.deleteProductItem(productItem)
                showRemoveDialog = false
            }
        }
    )
}
