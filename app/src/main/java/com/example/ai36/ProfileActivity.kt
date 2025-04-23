package com.example.ai36

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ai36.ui.theme.AI36Theme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { innerPadding ->
                ProfileBody(innerPadding)
            }

        }
    }
}

@Composable
fun ProfileBody(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            //method 1
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
//                tint = Color.Red,
                contentDescription = null, modifier = Modifier
                    .height(28.dp)
                    .width(28.dp)
            )

            Text(
                text = "Andrew", style = TextStyle(
                    fontSize = 15.sp,
//                textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
//                fontStyle = FontStyle.Italic,
                )
            )

            //method 2
            Icon(
                painter = painterResource(R.drawable.baseline_more_horiz_24),
//                tint = Color.Red,
                contentDescription = null, modifier = Modifier
                    .height(28.dp)
                    .width(28.dp)
                    .clickable {
                        //TO-DO
                    }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    ProfileBody(innerPadding = PaddingValues(0.dp))
}