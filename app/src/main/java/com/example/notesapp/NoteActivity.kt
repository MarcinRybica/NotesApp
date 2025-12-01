package com.example.notesapp

import android.R.attr.text
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.ui.theme.NotesAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.example.notesapp.model.Note
import com.example.notesapp.viewmodel.NoteViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import androidx.lifecycle.viewmodel.compose.viewModel

class NoteActivity : ComponentActivity() {
    private lateinit var realm: Realm
    private var currentNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = RealmConfiguration.Builder(schema = setOf(Note::class))
            .name("notes.realm")
            .build()
        realm = Realm.open(config)

        val noteIdHex = intent.getStringExtra("noteId")
        if(!noteIdHex.isNullOrEmpty()) {
            val objectId = org.mongodb.kbson.ObjectId(hexString = noteIdHex)
            currentNote = realm.query<Note>("_id == $0", objectId).first().find()
        }

        setContent{
            NotesAppTheme() {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFF000000)
                ) {
                    NoteScreen(currentNote, viewModel = viewModel())
                }
            }
        }
    }
}

@Composable
fun NoteScreen(note: Note?,
               viewModel: NoteViewModel = viewModel())
{
    var title by remember(note) { mutableStateOf(note?.title ?: "") }
    var text by remember(note) { mutableStateOf(note?.content ?: "") }
    val context = LocalContext.current

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (note != null) {
                        viewModel.editNote(note, title, text)
                    } else {
                        viewModel.addNote(title, text)
                    }
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text("<", color = Color.White, fontSize = 30.sp)
            }

            Text("NotesApp",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.weight(5f),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = {
                    note?.let {
                        viewModel.deleteNoteById(it._id )
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    tint = Color.White,
                    contentDescription = "Delete",
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(
                        3.dp,
                        Color(0xFFFF7700),
                        shape = MaterialTheme.shapes.medium)
                    .padding(3.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = {title = it},
                    label = null,
                    placeholder = {Text("Title...", color = Color(0xFFFF7700))},
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color(0xFF3D3D3D), //gray
                        unfocusedContainerColor = Color(0xFF3D3D3D)
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .border(
                    3.dp,
                    Color(0xFFFF7700),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(3.dp)
        ){
            OutlinedTextField(
                value = text,
                onValueChange = {text = it},
                label = null,
                placeholder = {Text("Note...", color = Color(0xFFFF7700))},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color(0xFF3D3D3D), //gray
                    unfocusedContainerColor = Color(0xFF3D3D3D)
                ),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}