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
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        AsyncImage(model = product.img1, contentDescription = null, modifier = Modifier.size(60.dp))

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = product.name, style = MaterialTheme.typography.headlineSmall)

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Price: ₹${product.price}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = "Rs/Gram: ${product.pricePerG}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = "NetWt: ${product.netWt}g",
                    style = MaterialTheme.typography.bodyMedium
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