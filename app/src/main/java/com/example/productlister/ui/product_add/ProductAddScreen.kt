package com.example.productlister.ui.product_add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.productlister.R
import com.example.productlister.ui.product_add.components.CameraButton
import com.example.productlister.ui.product_add.components.FormTextField
import com.example.productlister.ui.events.AddEvent
import com.example.productlister.ui.events.UiEvent
import io.grpc.android.BuildConfig
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

val LocalSnackbarHostState = compositionLocalOf {
    SnackbarHostState()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductAddScreen(
    navController: NavController,
    state: ProductAddState,
    onEvent: (AddEvent) -> Unit,
    eventFlow: SharedFlow<UiEvent>
) {
    fun Context.createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            externalCacheDir      /* directory */
        )
        return image
    }

    val snackbarHostState = LocalSnackbarHostState.current
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "com.example.productlister.provider", file
    )
    var capturedImageUri = remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri.value = uri
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = "AddScreen") {
        eventFlow.collectLatest { event: UiEvent ->
            when (event) {
                is UiEvent.SaveProduct -> {
                    navController.navigateUp()
                }

                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }

                else -> {}
            }
        }
    }

    CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "Add Product") }) },
            snackbarHost = { SnackbarHost(hostState = LocalSnackbarHostState.current) }
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(horizontal = 25.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CameraButton(
                        color = Color.Blue,
                        label = "Add picture",
                        modifier = Modifier
                            .clickable(onClick = {
                                val permissionCheckResult =
                                    ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
                                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                    cameraLauncher.launch(uri)
                                } else {
                                    // Request a permission
                                    permissionLauncher.launch(android.Manifest.permission.CAMERA)
                                }
                            })
                    )

                    if (state.img1.isNotBlank()) {
                        AsyncImage(
                            model = capturedImageUri,
                            contentDescription = null,
                            placeholder = painterResource(R.drawable.ic_launcher_background)
                        )
                    }
                    if (state.img1.isNotBlank()) {
                        AsyncImage(
                            model = state.img2,
                            contentDescription = null,
                            placeholder = painterResource(R.drawable.ic_launcher_background)
                        )
                    }
                    if (state.img1.isNotBlank()) {
                        AsyncImage(
                            model = state.img3,
                            contentDescription = null,
                            placeholder = painterResource(R.drawable.ic_launcher_background)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                FormTextField(
                    text = state.name,
                    placeholder = "Name",
                    onValueChange = {
                        onEvent(AddEvent.NameChanged(it))
                    },
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.outline_label_24),
                            contentDescription = null
                        )
                    })

                Spacer(modifier = Modifier.height(28.dp))

                val priceDisplayed = if (state.price == null || state.price.toString()
                        .isBlank()
                ) "" else state.price.toString()
                FormTextField(
                    text = priceDisplayed,
                    placeholder = "Price",
                    onValueChange = { newValue ->
                        val sanitisedText = newValue.filter { it.isDigit() }
                        onEvent(AddEvent.PriceChanged(sanitisedText))
                    },
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_currency_rupee_24),
                            contentDescription = null
                        )
                    })

                Spacer(modifier = Modifier.height(28.dp))

                val pricePerGDisplayed = if (state.pricePerG == null || state.pricePerG.toString()
                        .isBlank()
                ) "" else state.pricePerG.toString()
                FormTextField(
                    text = pricePerGDisplayed,
                    placeholder = "Price / Gram",
                    onValueChange = { newValue ->
                        val sanitisedText = newValue.filter {
//                            val regex = Regex("\"^0*[0-9]\\.[0-9]{1,2}\$\"")
                            val regex = Regex("\"^0*[0-9]\"")
                            return@filter !regex.matches(newValue)
                        }
                        onEvent(AddEvent.PricePerGChanged(sanitisedText))
                    },
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                    leadingIcon = {})

                Spacer(modifier = Modifier.height(28.dp))

                val netWtDisplayed = if (state.netWt == null || state.netWt.toString()
                        .isBlank()
                ) "" else state.netWt.toString()
                FormTextField(
                    text = netWtDisplayed,
                    placeholder = "Net Weight",
                    onValueChange = { newValue ->

                        val sanitisedText = newValue.filter { it.isDigit() }
                        onEvent(AddEvent.NetWtChanged(sanitisedText))

                    },
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_balance_24),
                            contentDescription = null
                        )
                    })

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onEvent(AddEvent.SaveProduct) },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = "SAVE",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductAddScreenPreview() {
    ProductAddScreen(
        navController = rememberNavController(),
        state = ProductAddState(),
        onEvent = {},
        eventFlow = MutableSharedFlow()
    )
}

private fun sanitizeFloatInput(input: String): String {
    val digitsOnly = input.filter { it.isDigit() }
    val decimalIndex = input.indexOfFirst { it == '.' }

    // Allow at most one decimal point
    return if (decimalIndex >= 0 && input.substring(decimalIndex + 1).contains('.')) {
        digitsOnly + input.substring(decimalIndex + 1).replace(".", "")
    } else {
        digitsOnly
    }
}