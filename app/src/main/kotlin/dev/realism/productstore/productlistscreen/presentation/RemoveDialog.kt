package dev.realism.productstore.productlistscreen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.realism.productstore.R
import dev.realism.productstore.ui.theme.RemoveDialogBackgroundColor
import dev.realism.productstore.ui.theme.RemoveDialogDismissTextColor


@Composable
fun RemoveDialog(onDismiss: () -> Unit, onRemove: () -> Unit) {
    AlertDialog(
        containerColor = RemoveDialogBackgroundColor,
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    stringResource(R.string.remove_dialog_no_text),
                    color = RemoveDialogDismissTextColor
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onRemove) {
                Text(
                    stringResource(R.string.remove_dialog_yes_text),
                    color = RemoveDialogDismissTextColor
                )
            }
        },
        icon = {
            Image(
                painter = painterResource(R.drawable.ic_round_warning_24),
                contentDescription = stringResource(R.string.products_count_ic_cd)
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.remove_product_text),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Text(
                    text = stringResource(R.string.remove_product_question),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                )
            }
        }
    )
}