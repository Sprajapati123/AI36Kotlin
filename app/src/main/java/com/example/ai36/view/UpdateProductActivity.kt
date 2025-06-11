package com.example.ai36.view

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ai36.model.ProductModel
import com.example.ai36.repository.ProductRepositoryImpl
import com.example.ai36.view.ui.theme.AI36Theme
import com.example.ai36.viewmodel.ProductViewModel

class UpdateProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           UpdateProductBody()
        }
    }
}

@Composable
fun UpdateProductBody() {
    var pName by remember { mutableStateOf("") }
    var pPrice by remember { mutableStateOf("") }
    var pDesc by remember { mutableStateOf("") }
    val context = LocalContext.current
    val activity = context as? Activity

    val productId : String? = activity?.intent?.getStringExtra("productId")

    val repo = remember { ProductRepositoryImpl() }
    val viewModel = remember { ProductViewModel(repo) }

    val products = viewModel.products.observeAsState(initial = null)

    LaunchedEffect(Unit) {
        viewModel.getProductById(productId.toString())
    }

    pName = products.value?.productName ?: ""
    pDesc = products.value?.description ?: ""
    pPrice = products.value?.price.toString()


    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            OutlinedTextField(
                value = pName, onValueChange = {
                    pName = it
                }, placeholder = {
                    Text("Product name")
                }, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = pDesc, onValueChange = {
                    pDesc = it
                }, placeholder = {
                    Text("Product Description")
                }, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))


            OutlinedTextField(
                value = pPrice, onValueChange = {
                    pPrice = it
                }, placeholder = {
                    Text("Product Price")
                }, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                var data = mutableMapOf<String,Any?>()

                data["productId"] = products.value?.productId
                data["productName"] = pName
                data["price"] = pPrice.toDouble()
                data["description"] = pDesc

                viewModel.updateProduct(products.value?.productId.toString(),data){
                    success,msg->
                    if (success) {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        activity?.finish()
                    } else {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text("Update product")
            }

        }
    }
}

@Preview
@Composable
fun previewUpdate(){
    UpdateProductBody()
}