package com.example.productlister.ui.product_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.productlister.domain.model.Product
import java.util.Date

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductListItem(
    product: Product,
    onEdit: (Product) -> Unit,
    onDelete: (Product) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(onClick = { onEdit(product) }, onLongClick = { onDelete(product) })
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        AsyncImage(model = product.img1, contentDescription = null, modifier = Modifier.size(60.dp).weight(0.3f))

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.fillMaxWidth().weight(1f), verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = product.name, style = MaterialTheme.typography.headlineSmall)

            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Price: ₹${product.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(0.9f)
                )
                Text(
                    text = "₹/g: ${product.pricePerG}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(0.8f)
                )
                Text(
                    text = "NetWt: ${product.netWt}g",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductListItemPreview() {
    ProductListItem(
        product = Product(123, "123", 123, 123.0, 123, "", "", "", Date(), Date()),
        onEdit = {},
        onDelete = {})
}