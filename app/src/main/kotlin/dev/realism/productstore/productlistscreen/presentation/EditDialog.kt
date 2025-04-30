package dev.realism.productstore.productlistscreen.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.realism.productstore.R
import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.ui.theme.LightPurple40
import dev.realism.productstore.ui.theme.Purple

@Composable
fun EditDialog(
    productItem: ProductItem,
    onDismiss: () -> Unit,
    onUpdate: (productItem: ProductItem) -> Unit
) {
    var itemAmount by remember { mutableIntStateOf(productItem.amount) }

    AlertDialog(
        containerColor = LightPurple40,
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    stringResource(R.string.alert_cancel_text),
                    color = Purple
                )
            }
        },
        confirmButton = {
            val newProductItem = productItem.copy(amount = itemAmount)
            TextButton(onClick = { onUpdate(newProductItem) }) {
                Text(
                    stringResource(R.string.alert_accept_text),
                    color = Purple
                )
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = stringResource(R.string.products_count_ic_cd),
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
                    text = stringResource(R.string.products_amount_text),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.RemoveCircleOutline,
                        contentDescription = stringResource(R.string.products_count_ic_cd),
                        tint = Purple,
                        modifier = Modifier
                            .scale(1.4f)
                            .clickable {
                                if (itemAmount > 0)
                                    itemAmount--
                            }
                    )
                    Text(
                        text = itemAmount.toString(),
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
                        contentDescription = stringResource(R.string.products_count_ic_cd),
                        tint = Purple,
                        modifier = Modifier
                            .scale(1.5f)
                            .clickable {
                                itemAmount++
                            }
                    )
                }
            }
        }
    )
}