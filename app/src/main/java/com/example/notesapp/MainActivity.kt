package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesapp.ui.theme.NotesAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFF000000)
                ) { innerPadding ->
                    NotesMain()
                }
            }
        }
    }
}

@Composable
fun NotesMain(modifier: Modifier = Modifier) {
    var text by remember{ mutableStateOf("") }
    var searchByButtonText by remember { mutableStateOf("Search by: TITLE") }
    val context = LocalContext.current

    Column(

    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
        ){
            Text(
                fontSize = 30.sp,
                text = "NotesApp",
                color = Color.White,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(
                modifier = Modifier
                    .weight(2f)
                    .border(
                        width = 3.dp,
                        color = Color(0xFFFF7700),
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(3.dp)
            ){
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = null,
                    placeholder = {Text("Search...", color = Color(0xFFFF7700))},
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color(0xFF3D3D3D), //gray
                        unfocusedContainerColor = Color(0xFF3D3D3D)
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if(searchByButtonText == "Search by: TITLE"){
                        searchByButtonText = "Search by: TAGS"
                    } else {
                        searchByButtonText = "Search by: TITLE"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = buttonColors(
                    containerColor = Color(0xFFFF7700)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(searchByButtonText,
                    color = Color.White)
            }
        }



        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
                .border(
                    width = 3.dp,
                    color = Color(0xFFFF7700),
                    shape = MaterialTheme.shapes.medium
                )
                .background(Color(0xFF3D3D3D))
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

            }

            Button(
                onClick = {
                    context.startActivity(Intent(context, NoteActivity::class.java))
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .height(96.dp)
                    .width(96.dp)
                    .padding(16.dp),
                colors = buttonColors(
                    containerColor = Color(0xFFFF7700)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("+",
                    color = Color.White,
                    fontSize = 32.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInput(){
    NotesAppTheme {
        NotesMain()
    }
}