package com.example.productlister.ui.product_add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productlister.R

@Composable
fun CameraButton(color: Color, label: String, modifier: Modifier) {

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_add_a_photo_24),
                contentDescription = "Camera Icon",
                tint = Color.White,
                modifier = Modifier
                    .size(64.dp)
                    .padding(bottom = 5.dp, end = 5.dp)
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 8.dp),
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun CameraButtonPreview() {
    CameraButton(Color.Blue, "Add picture", modifier = Modifier)
}