package com.ingrid.mercadolibre.presentation.screens.detail.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ingrid.mercadolibre.R
import com.ingrid.mercadolibre.ui.theme.BlueML

@Composable
fun AvailableStock(
    context: Context,
    quantity: String,
    cart: (String) -> Unit,
    availableQuantity: Int,
) {
    Text(
        text = stringResource(R.string.text_available_stock),
        fontSize = 16.sp,
        modifier = Modifier.padding(vertical = 20.dp),
        color = Color.Black
    )
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray.copy(alpha = 0.1f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .height(50.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = { }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {

                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.text_quantity))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(if (quantity.isEmpty()) "1" else " ${quantity.toInt() + 1}")
                    }
                    withStyle(style = SpanStyle(color = Color.Gray.copy(alpha = 0.5f))) {
                        append(stringResource(R.string.text_number_available, availableQuantity))
                    }
                },
                color = Color.Black
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = "",
                tint  = Color(0xFF130483),
            )
        }
    }
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor =  BlueML,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            Toast.makeText(context, context.getText(R.string.text_ready_to_shop), Toast.LENGTH_SHORT)
                .show()
        }
    ) {
        Text(text = stringResource(R.string.text_shop_now), color = Color.White)
    }
    Spacer(modifier = Modifier.height(8.dp))
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = BlueML
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            if (quantity.isNotEmpty()) cart((quantity.toInt() + 1).toString())
            else cart(context.getText(R.string.text_default_value).toString())
        }
    ) {
        Text(text = stringResource(R.string.button_add_cart), color =  Color(0xFF130483))
    }
}