package com.example.ai36.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ai36.repository.ProductRepositoryImpl
import com.example.ai36.viewmodel.ProductViewModel

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardBody()
        }
    }
}

@Composable
fun DashboardBody() {
    val context = LocalContext.current
    val activity = context as? Activity

    val repo = remember { ProductRepositoryImpl() }
    val viewModel = remember { ProductViewModel(repo) }

    val products = viewModel.allProducts
        .observeAsState(initial = emptyList())

    val loading = viewModel.loading.observeAsState(initial = false)

    LaunchedEffect(Unit) {
        viewModel.getAllProduct()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val intent = Intent(
                    context,
                    AddProductActivity::class.java
                )
                context.startActivity(intent)
            }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            if (loading.value == true) {
                item {
                    Box (
                        modifier = Modifier.height(500.dp).fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }
            } else {
                items(products.value.size) { index ->
                    var data = products.value[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                AsyncImage(
                                    model = data?.productImage,
                                    contentDescription = null,
                                    modifier = Modifier.weight(1f)
                                )
                                Column(

                                    modifier = Modifier.weight(3f).padding(10.dp)
                                ) {
                                    Text("${data?.productName}", style = TextStyle(fontSize = 20.sp))
                                    Text("${data?.price}", style = TextStyle(fontSize = 20.sp))
                                    Text("${data?.description}", style = TextStyle(fontSize = 20.sp))
                                }

                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(
                                    onClick = {
                                        val intent = Intent(context, UpdateProductActivity::class.java)
                                        intent.putExtra("productId",data?.productId.toString())
                                        context.startActivity(intent)
                                    },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        contentColor = Color.Gray
                                    )
                                ) {
                                    Icon(Icons.Default.Edit, contentDescription = null)

                                }

                                IconButton(
                                    onClick = {
                                        viewModel.deleteProduct(data?.productId.toString()) { success, message ->
                                            if (success) {
                                                Toast.makeText(context, message, Toast.LENGTH_SHORT)
                                                    .show()
                                            } else {
                                                Toast.makeText(context, message, Toast.LENGTH_SHORT)
                                                    .show()
                                            }
                                        }
                                    },
                                    colors = IconButtonDefaults.iconButtonColors(
                                        contentColor = Color.Red
                                    )
                                ) {
                                    Icon(Icons.Default.Delete, contentDescription = null)
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}

@Preview
@Composable
fun DashboardPreview() {
    DashboardBody()
}