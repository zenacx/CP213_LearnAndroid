package com.example.a510lablearbandaroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Column (modifier = Modifier.fillMaxSize().background(color = Color.Gray).padding(32.dp)){
                // hp
                Box(modifier = Modifier
                    .height(32.dp)
                    .background(color = Color.White)
                    .fillMaxWidth()


                ){
                    Text(
                        text = "HP",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .fillMaxWidth(fraction = 0.10f)
                            .background(color =  Color.Red)
                            .padding(8.dp)
                    )
                }
                // image
                Image(
                    painter = painterResource(id = R.drawable.beast),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(size = 200.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                )
                //status
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ){
                    Column {
                        Text(text = "STR", fontSize = 32.sp)
                        Text(text = "10", fontSize = 32.sp)
                    }
                    Column {
                        Text(text = "AGI", fontSize = 32.sp)
                        Text(text = "10", fontSize = 32.sp)
                    }
                    Column {
                        Text(text = "INT", fontSize = 32.sp)
                        Text(text = "10", fontSize = 32.sp)
                    }
                }
            }
        }
    }
}
